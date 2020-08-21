-- Create table
create table TEST_DEMO
(
  id          NUMBER(18) not null,
  name        VARCHAR2(15),
  create_date DATE
)
tablespace DSJ
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table
comment on table TEST_DEMO
  is '示例表';
-- Add comments to the columns
comment on column TEST_DEMO.id
  is '主键';
comment on column TEST_DEMO.name
  is '名字';
comment on column TEST_DEMO.create_date
  is '创建时间';
-- Create/Recreate primary, unique and foreign key constraints
alter table TEST_DEMO
  add constraint PK_TEST_DEMO_ID primary key (ID)
  using index
  tablespace DSJ
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

insert into test_demo (ID, NAME, CREATE_DATE)
values (1, '张三', to_date('21-08-2020 02:01:00', 'dd-mm-yyyy hh24:mi:ss'));
