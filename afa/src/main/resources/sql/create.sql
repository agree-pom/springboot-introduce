--declare
--    i integer ;
--begin
--    select count(1) into i from user_tables where table_name = 'user_info';
--if i > 0 then
--    dbms_output.put_line('表user_info已存在 ...');
--end if;
--    dbms_output.put_line('表user_info不存在，执行创建 ...');
--    execute immediate '';
--end;
--
--
--
--
----create table user_infoa



select * from user_info;