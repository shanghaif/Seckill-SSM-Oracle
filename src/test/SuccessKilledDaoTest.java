package test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dao.SuccessKilledDao;
import com.seckill.entity.SuccessKilled;

/**
 * @author xiaobai
 * @date 2016��11��6������4:20:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {
	
	@Resource
	private SuccessKilledDao dao;
	
	/**
	 * seckillId��userPhone����ͬʱ�Ż�Υ��ΨһԼ������
	 * @author xiaobai
	 * @date 2016��11��6������5:27:34
	 */
	@Test
	public void testInsertSuccessKilled() throws Exception{
		long seckillId = 1L;
		long userPhone = 1312333;
		int i = dao.insertSuccessKilled(seckillId, userPhone);
		System.out.println("insertCount:" + i);
	}
	
	@Test
	public void testQueryByIdWithSeckill()throws Exception{
		long seckillId = 1L;
		long userPhone = 1434234;
		SuccessKilled successKilled = dao.queryByIdWithSeckill(seckillId, userPhone);
		System.out.println(successKilled);
		System.out.println(successKilled.getSeckill());
	}
}