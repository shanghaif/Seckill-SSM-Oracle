package com.seckill.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.seckill.dao.SeckillDao;
import com.seckill.dao.SuccessKilledDao;
import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.entity.SuccessKilled;
import com.seckill.enums.SeckillStatEnum;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;
import com.seckill.service.SeckillService;

/**
 * @author xiaobai
 * @date 2016��11��6������6:16:52
 */
// @Component ͨ��@
@Service
public class SeckillServiceImpl implements SeckillService {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	// ע��service����
	// @Resource
	@Autowired
	private SeckillDao seckillDao;

	@Autowired
	private SuccessKilledDao successKilledDao;

	// md5��ֵ�ַ���,��������md5
	private final String slat = "asdasdSae34@#%fd45#$fwdd21323sd";

	/**
	 * @date 2016��11��6������6:27:29
	 */
	@Override
	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(1, 5);
	}

	/**
	 * @date 2016��11��6������6:27:29
	 */
	@Override
	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	/**
	 * ��ɱ����ʱ����ӿڵ�ַ �������ϵͳʱ�����ɱʱ��
	 * 
	 * @author xiaobai
	 * @date 2016��11��6������6:27:29
	 * @param seckillId
	 */
	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = seckillDao.queryById(seckillId);
		if (seckill == null) {
			return new Exposer(false, seckillId);
		}
		Date startTime = seckill.getStartTime();
		Date endTime = seckill.getEndTime();
		// ��ǰϵͳʱ��
		Date nowTime = new Date();
		/*
		 * if(nowTime.getTime() < startTime.getTime() || nowTime.getTime() >
		 * endTime.getTime() ){
		 */
		if (1478275920123L < startTime.getTime()
				|| 1478275920123L > endTime.getTime()) {
			return new Exposer(false, seckillId, nowTime.getTime(),
					startTime.getTime(), endTime.getTime());
		}
		// ת���ض��ַ������̣�������
		String md5 = getMd5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	/**
	 * md5����
	 * 
	 * @author xiaobai
	 * @date 2016��11��6������6:43:30
	 * @param seckillId
	 * @return
	 */
	private String getMd5(long seckillId) {
		String base = seckillId + "/" + slat;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	/**
	 * ִ����ɱ����
	 * 
	 * @author xiaobai
	 * @date 2016��11��6������6:27:29
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	@Override
	@Transactional
	/*
	 * ʹ��ע��������񷽷����ŵ㣺 1�������ŶӴ��һ��Լ������ȷ�������񷽷��ı�ɷ��
	 * 2����֤���񷽷���ִ��ʱ�価���ܶ�,��Ҫ�����������������,RPC/HTTP������߰��뵽���񷽷��ⲿ
	 * 3:�������еķ�������Ҫ����,��ֻ��һ���޸Ĳ�����ֻ����������Ҫ�������
	 */
	public SeckillExecution executeSeckill(long seckillId, long userPhone,
			String md5) throws SeckillCloseException, RepeatKillException,
			SeckillException {
		if (md5 == null || !md5.equals(getMd5(seckillId))) {
			throw new SeckillException(
					"###########seckill data rewrite###########");
		}
		// ִ����ɱ�߼�������� + ������Ϊ
		Date nowTime = new Date();

		try {
			// �����
			int updateCount = seckillDao.reduceNum(seckillId, nowTime);
			if (updateCount <= 0) {
				// û�и��µ���¼����ɱ����
				throw new SeckillCloseException(
						"###########seckill is closed###########");
			} else {
				// ��¼������Ϊ
				int insertCount = successKilledDao.insertSuccessKilled(
						seckillId, userPhone);
				// ΨһseckillId,userPhone
				if (insertCount <= 0) {
					// �ظ���ɱ
					throw new RepeatKillException(
							"###########seckill repeated###########");
				} else {
					// ��ɱ�ɹ�
					SuccessKilled successKilled = successKilledDao
							.queryByIdWithSeckill(seckillId, userPhone);
					return new SeckillExecution(seckillId,
							SeckillStatEnum.SUCCESS, successKilled);
				}
			}
		} catch (SeckillCloseException e1) {
			log.error(e1.getMessage(), e1);
			throw e1;
		} catch (RepeatKillException e2) {
			log.error(e2.getMessage(), e2);
			throw e2;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			// ���б������쳣��ת��Ϊ�������쳣
			throw new SeckillException("seckill inner error:" + e.getMessage());
		}
	}
}