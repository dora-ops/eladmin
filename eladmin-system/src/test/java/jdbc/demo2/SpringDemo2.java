package jdbc.demo2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext2.xml")
public class SpringDemo2 {
	
	/**
	 * 注入代理类
	 */
	@Resource(name="accountServiceProxy")
	private AccountService accountService;
	
	@Test
	public void demo(){
		accountService.transfer("aaa", "bbb", 200d);
	}
}
