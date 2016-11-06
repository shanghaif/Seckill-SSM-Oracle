package com.seckill.service;

import java.util.List;

import com.seckill.dto.Exposer;
import com.seckill.dto.SeckillExecution;
import com.seckill.entity.Seckill;
import com.seckill.exception.RepeatKillException;
import com.seckill.exception.SeckillCloseException;
import com.seckill.exception.SeckillException;

/**
 * ҵ��ӿڣ�վ��"ʹ����"�Ƕ���ƽӿ�
 * �������棺������������,����,��������(return ����(�Ѻ�)/�쳣)
 * @author xiaobai
 * @date 2016��11��6������5:50:36
 */
public interface SeckillService {
	
	/**
	 * ��ѯ������ɱ��¼
	 * @author xiaobai
	 * @date 2016��11��6������5:55:12
	 * @return
	 */
	List<Seckill> getSeckillList();
	
	/**
	 * ����id��ѯ��ɱ��¼
	 * @author xiaobai
	 * @date 2016��11��6������6:23:52
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);
	
	/**
	 * ��ɱ����ʱ����ӿڵ�ַ
	 * �������ϵͳʱ�����ɱʱ��
	 * @author xiaobai
	 * @date 2016��11��6������5:55:48
	 * @param seckillId
	 */
	Exposer exportSeckillUrl(long seckillId);
	
	/**
	 * ִ����ɱ����
	 * @author xiaobai
	 * @date 2016��11��6������6:15:44
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 * @throws SeckillCloseException	��ɱ�ر��쳣
	 * @throws RepeatKillException		�ظ���ɱ�쳣
	 * @throws SeckillException			��ɱ�쳣
	 */
	SeckillExecution executeSeckill(long seckillId,long userPhone,String md5) 
			throws SeckillCloseException,RepeatKillException,SeckillException;
}
