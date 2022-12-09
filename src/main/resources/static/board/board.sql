select * from board;
select * from normal_board;

insert into board values('Board',2,'자유게시판','바보',0,'몽충이',0,sysdate,null);
insert into board values('NormalBoard',3,'자유게시판','바보',0,'몽충이',0,sysdate,null);
insert into board values('NormalBoard',4,'FREE_BOARD','바보',0,'몽충이',0,sysdate,null);
insert into board values('normal_board',5,'FREE_BOARD','바보',0,'몽충이',0,sysdate,null);


SELECT a.session_id AS SESSION_ID, b.serial# AS SERIAL_NO,
       a.os_user_name AS OS_USER_NAME, a.oracle_username AS ORACLE_USERNAME, b.status AS STATUS
FROM v$locked_object a, v$session b
WHERE a.session_id = b.sid;

alter system kill session '289,25473';

commit;