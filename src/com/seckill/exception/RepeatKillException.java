package com.seckill.exception;

/**
 * �ظ���ɱ�쳣���������쳣��
 * @author xiaobai
 * @date 2016��11��6������6:07:19
 */
@SuppressWarnings("serial")
public class RepeatKillException  extends SeckillException{

	public RepeatKillException(String message) {
		super(message);
	}

	public RepeatKillException(String message, Throwable cause) {
		super(message, cause);
	}
}
