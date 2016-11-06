package com.seckill.dao;

import org.apache.ibatis.annotations.Param;

import com.seckill.entity.SuccessKilled;

/**
 * DAO�� ���ݷ��ʲ�
 * @author xiaobai
 * @date 2016��11��5������6:43:49
 */
public interface SuccessKilledDao {
	
	/**
	 * ���빺����ϸ���ɹ����ظ�
	 * @author xiaobai
	 * @date 2016��11��5������6:44:30
	 * @param seckillId
	 * @param userPhone
	 * @return ���������
	 */
	int insertSuccessKilled(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
	
	/**
	 * ����ID��ѯSuccessKilled��Я����ɱ��Ʒ����ʵ��
	 * @author xiaobai
	 * @date 2016��11��5������6:45:37
	 * @param seckillId
	 * @return
	 */
	SuccessKilled queryByIdWithSeckill(@Param("seckillId")long seckillId,@Param("userPhone")long userPhone);
}