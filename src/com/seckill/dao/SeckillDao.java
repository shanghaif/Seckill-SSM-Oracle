package com.seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.seckill.entity.Seckill;

/**
 * DAO�� ���ݷ��ʲ�
 * @author xiaobai
 * @date 2016��11��5������6:38:51
 */
public interface SeckillDao {
	/**
	 * �����
	 * @author xiaobai
	 * @date 2016��11��5������6:40:05
	 * @param seckillId
	 * @param killTime
	 * @return ���Ӱ������>1����ʾ���µļ�¼����
	 */
	//����������ʱ�����@Paramע��
	int reduceNum(@Param("seckillId")long seckillId,@Param("killTime") Date killTime);
	
	/**
	 * ����ID��ѯ��ɱ����
	 * @author xiaobai
	 * @date 2016��11��5������6:40:56
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * ��ҳ��ѯ��ɱ�б�
	 * @author xiaobai
	 * @date 2016��11��5������6:42:50
	 * @param start	��ѯ��ʼ����
	 * @param end	��ѯ��ֹ����
	 * @return
	 */
	List<Seckill> queryAll(@Param("start") int start,@Param("end")int end);
}