package com.seckill.exception;

/**
 * ��ɱ����쳣
 * @author xiaobai
 * @date 2016��11��6������6:12:20
 */
@SuppressWarnings("serial")
public class SeckillException extends RuntimeException{

	public SeckillException(String message) {
		super(message);
	}

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}
}
