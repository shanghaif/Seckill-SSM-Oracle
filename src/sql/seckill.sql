--�����û�
create user seckill	identified by seckill;
--��Ȩ
grant resource,connect,create view to seckill;


--������ɱ����
create table seckill(
       seckill_id  number PRIMARY KEY,
       name varchar2(120) not null,
       num number(3) not null,					  --oracle�в���ʹ�ùؼ���number�������������Ϊnum
       start_time Timestamp  not null,            --ʱ���Ϊtimestamp��ʽ
       end_time timestamp  not null,
       create_time timestamp default systimestamp --Ĭ��ϵͳʱ���
       );
--��������
create sequence seq_seckill;
--��������
create index idx_start_time on seckill(start_time);
create index idx_end_time on seckill(end_time);
create index idx_create_time on seckill(create_time);
--���ע��
COMMENT ON TABLE seckill IS '��ɱ����';
COMMENT ON COLUMN seckill.seckill_id IS '��Ʒ���ID';
COMMENT ON COLUMN seckill.name IS '��Ʒ����'; 
COMMENT ON COLUMN seckill.num IS '�������'; 
COMMENT ON COLUMN seckill.start_time IS '��ɱ����ʱ��'; 
COMMENT ON COLUMN seckill.end_time IS '��ɱ����ʱ��'; 
COMMENT ON COLUMN seckill.create_time IS '����ʱ��'; 
--��ʼ������
--����ʱ������ʱ��ֻ��ȷ���룬�ʴ�ʱto_date��to_timestampЧ����ͬ������ѡ��ȷ��Χ
insert into seckill
       (seckill_id,name,num,start_time,end_time)  values 
       (seq_seckill.nextval,'1000Ԫ��ɱiphone6', 100, 
        to_date('2016-11-05 12:00:00','yyyy-mm-dd hh24:mi:ss'), to_timestamp('2016-11-06 00:00:12','yyyy-mm-dd hh24:mi:ss'));
insert into seckill
       (seckill_id,name,num,start_time,end_time)  values 
       (seq_seckill.nextval,'500Ԫ��ɱipad2', 200, 
        to_date('2016-11-05 00:12:00','yyyy-mm-dd hh24:mi:ss'), to_timestamp('2016-11-06 00:12:00','yyyy-mm-dd hh24:mi:ss'));
insert into seckill
       (seckill_id,name,num,start_time,end_time)  values 
       (seq_seckill.nextval,'300Ԫ��ɱС��4', 300, 
        to_date('2016-11-05 00:00:12','yyyy-mm-dd hh24:mi:ss'), to_timestamp('2016-11-06 12:00:00','yyyy-mm-dd hh24:mi:ss'));
insert into seckill
       (seckill_id,name,num,start_time,end_time)  values 
       (seq_seckill.nextval,'200Ԫ��ɱ����note', 400,
        to_date('2016-11-05 00:00:01','yyyy-mm-dd hh24:mi:ss'), to_timestamp('2016-11-06 00:00:12','yyyy-mm-dd hh24:mi:ss'));
--�ύ����
commit
/*
  ��oracle���ݿ�ֱ�Ӳ�ѯtimestamp��ʽʱ��ʾΪ�������ڸ�ʽ�������ϳ���ϰ�ߣ���ѯʱ��ͨ��to_charת��
  �����н̳�ͨ����oracle��ע�������� NLS_TIMESTAMP_FORMAT = YYYY-MM-DD HH24:MI:SS:FF6 
  �ַ����������ڣ�δ�ɹ�-.-,����Ȥ���аٶȣ��ɹ����봫�����¾���
*/
select to_char(start_time,'yyyy-mm-dd hh24:mi:ss:ff6'),
       to_char(end_time,'yyyy-mm-dd hh24:mi:ss:ff6'),
       to_char(create_time,'yyyy-mm-dd hh24:mi:ss:ff6') from seckill;

--��ѯoracleĬ��timestamp�����ʽ��nls_timestamp_format
select value from nls_session_parameters where parameter = 'nls_timestamp_format'



--��ɱ�ɹ���ϸ��
--�û���½��֤��ص���Ϣ
create table success_killed(
       seckill_id  number,
       user_phone varchar2(120) not null,
       state number(1) default -1,
       create_time timestamp default systimestamp,
       primary  key (seckill_id,user_phone) /*��������*/
       
);
--���ע��
COMMENT ON TABLE success_killed IS '��ɱ�ɹ���ϸ��';
COMMENT ON COLUMN success_killed.seckill_id IS '��ɱ��Ʒid';
COMMENT ON COLUMN success_killed.user_phone IS '�û��ֻ���';
COMMENT ON COLUMN success_killed.state IS '״̬��ʾ��-1��Ч 0���ɹ� 1���Ѹ��� 2���ѷ���';
COMMENT ON COLUMN success_killed.create_time IS '����ʱ��';

--��������
create index idx_create_time on success_killed(create_time);