package test;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.seckill.dao.SeckillDao;
import com.seckill.entity.Seckill;

/**
 * @author xiaobai
 * @date 2016��11��6������12:06:28
 */
/**
 * ����spring��junit���ϣ�junit����ʱ����springIOC����
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//����junit spring�����ļ�
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
	
	//ע��DAOʵ��������
	@Resource//j2ee�Դ���ע�⣬��spring��@Autowiredע������
	private SeckillDao seckilldao;
	
	@Test
	public void testQueryById() throws Exception{
		long id = 2;
		Seckill seckill = seckilldao.queryById(id);
		System.out.println(seckill.getName());
		System.out.println(seckill);
	}
	
	@Test
	public void testQueryAll() throws Exception{
		//javaû�б����βεļ�¼
		List<Seckill> list = seckilldao.queryAll(1, 4);
		for(Seckill a : list){
			System.out.println(a);
		}
	}
	
	@Test
	public void testReduceNum() throws Exception{
		Date date = new Date();
		int i = seckilldao.reduceNum(2, date);
		System.out.println("updateCount:"+i);
	}
}