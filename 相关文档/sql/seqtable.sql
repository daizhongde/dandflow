prompt PL/SQL Developer import file
prompt Created on 2014年10月20日 by d144574
set feedback off
set define off
prompt Creating SEQTABLE...
create table SEQTABLE
(
  seq_name  VARCHAR2(30) not null,
  seq_value INTEGER not null,
  seq_time  VARCHAR2(30) not null,
  flag      VARCHAR2(4)
)
tablespace WL_D_TABLE
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 128K
    minextents 1
    maxextents unlimited
  );
alter table SEQTABLE
  add constraint PK_SEQUENCES primary key (SEQ_NAME)
  using index 
  tablespace WL_D_INDEX
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 128K
    next 128K
    minextents 1
    maxextents unlimited
  );

prompt Disabling triggers for SEQTABLE...
alter table SEQTABLE disable all triggers;
prompt Deleting SEQTABLE...
delete from SEQTABLE;
commit;
prompt Loading SEQTABLE...
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_package', 10, '2013-11-04', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_customeraudit', 1, '2014-04-23', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_customeraudit_g', 22, '2014-03-03', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_customerbaobei', 2, '2014-04-14', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_customerbaobei_g', 2, '2013-05-27', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_customerprice', 1, '2014-04-23', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_ordercancel', 1, '2014-05-12', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_customer', 695, '2014-04-23', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_jobplanyear', 1, '2013-05-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_jobplanweek', 7, '2013-08-26', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_jobplanweek_g', 7, '2013-08-26', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_jobplanyear_g', 1, '2013-05-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_jobplanmonth', 1, '2013-08-18', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_jobplanmonth_g', 2, '2013-07-05', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_verification_g', 4, '2013-05-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_maintenance_g', 1, '2013-07-02', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_cardcharge', 2, '2014-03-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_cardcharge_g', 1, '2013-05-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_materielrequest', 1, '2013-05-12', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_materielrequest_dt', 1, '2013-05-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_insuranceitem', 2, '2013-04-21', '58');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_carcharge', 2, '2013-05-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_billclient', 4, '2013-11-05', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sys_user', 806, '2014-03-04', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sys_role', 151, '2014-09-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('city', 2808, '2014-05-19', '34');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('company', 12808, '2014-10-10', '35');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('customer', 158, '2013-05-21', '36');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('dictionary', 38, '2008-05-16', '41');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('station', 359, '2014-03-12', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_order', 1, '2014-10-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_assign', 1, '2014-10-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_adjustamt', 1, '2010-12-02', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_billsite', 1, '2014-03-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('province', 32, '2008-05-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('site', 268, '2014-02-26', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stationtype', 1, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sys_user_log', 24, '2013-05-12', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffic_indemnity', 1, '2009-04-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('customer_givroute', 0, '2007-10-18', '38');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffic_recoup', 1, '2013-12-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sys_broad', 3, '2011-12-08', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffic', 1, '2014-08-20', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_openrequest', 1, '2014-03-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffic_insurance', 0, '2007-10-19', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffic_supplier', 15, '2009-04-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('customer_auqeroute', 1, '2007-12-30', '37');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_income', 6, '2012-10-01', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stream_model_dt', 752, '2013-09-23', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_safecheckcar', 1, '2008-06-02', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomecancel', 2, '2010-05-20', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_charge', 3, '2014-03-25', '52');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_charge_g', 2, '2013-05-27', '54');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_persmoney', 1, '2010-06-11', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_moneyaccount', 2, '2013-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_roadtrace', 2, '2013-08-26', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_addserver', 0, '2007-10-23', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('loadman', 0, '2007-12-01', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('supplier', 0, '2010-05-06', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sys_log', 5, '2014-10-17', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_settle', 8, '2013-04-18', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('account', 21, '2013-11-16', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_safecheckcar_g', 0, '2007-10-30', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stream_model', 5, '2009-07-31', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stream', 13, '2014-10-17', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stream_dot', 0, '2007-01-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_cashrequest', 1, '2010-09-28', '51');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('subitem', 322, '2013-11-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_cashdaily', 1, '2014-10-19', '48');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('goodstype', 65, '2010-05-14', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('goods', 3, '2008-11-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_chargeplan', 2, '2013-05-27', '58');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomeadd', 1, '2010-11-18', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomeadd_g', 1, '2010-11-18', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sys_customeruser', 24, '2013-08-26', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_moneymovecard', 1, '2012-12-05', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_changesite', 1, '2008-06-01', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomerec', 0, '2007-10-19', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomerec_g', 0, '2007-10-19', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_billtrace', 12, '2012-12-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_cashcheck', 2, '2008-05-10', '46');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_settle_g', 1, '2009-09-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_goods', 4, '2008-05-11', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_model', 0, '2007-12-30', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_order_g', 3, '2014-02-08', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_moneymove', 2, '2013-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_spsettle', 80, '2014-10-17', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_carfee', 1, '2009-04-04', '29');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_carfee_g', 1, '2009-04-04', '31');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_spsettle_g', 1, '2010-12-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('job', 7, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_employee', 205, '2012-11-24', '13');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomerate', 1, '2008-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomerate_g', 5, '2008-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_qtyrange', 2, '2008-05-20', '23');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('incomeunit', 7, '2008-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('deliverier', 3, '2008-06-13', '39');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('driver', 4, '2008-06-03', '42');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_driverrate', 1, '2008-07-07', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_delivierrate', 1, '2008-07-07', null);
commit;
prompt 100 records committed...
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_supplierrate', 220, '2008-05-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_goodstype', 42, '2008-09-11', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sys_filemanager', 10, '2014-03-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_duetype', 1, '2008-06-12', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('personal_unit', 2, '2008-06-14', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_outpersonal', 3, '2010-06-20', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_outpersonal_g', 1, '2010-06-26', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('carstuff', 0, '2010-02-05', '33');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('loadteam', 13, '2010-02-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffic_handle', 5, '2009-04-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_driverfee', 1, '2008-07-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_drsettle', 1, '2009-05-11', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_deliverierfee', 1, '2009-04-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_losettle', 1, '2009-05-12', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('location', 29, '2010-09-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('deposit', 1, '2010-06-27', '40');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('eventtype', 7, '2008-10-17', '43');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_deposit_g', 1, '2010-06-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_adjustamt_g', 1, '2010-12-02', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_carfee_f', 2, '2009-04-04', '30');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_carfee_trace', 2, '2009-04-04', '32');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_otheritem', 10, '2009-09-17', '21');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_loadword', 2, '2010-02-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomebalm', 1, '2014-09-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_payapply', 2, '2009-09-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_commission', 1, '2013-05-27', '59');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('rate_supplier', 24, '2010-10-23', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('rate_customer', 583, '2013-10-24', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('rate_loadcost', 2, '2009-12-04', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('rate_loadincome', 1, '2009-12-04', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_costitem', 9, '2009-09-17', '11');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_guaranteeslip', 1, '2012-12-20', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_claimfile', 5, '2008-10-17', '10');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffic_trace', 1, '2014-02-21', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffic_claim', 2, '2010-04-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_goodsfile', 13, '2013-12-24', '16');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffic_share', 1, '2012-09-18', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffic_obtaintrace', 2, '2010-01-26', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffic_obtain', 1, '2010-04-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_orderitem', 33, '2009-09-22', '20');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_assignitem', 27, '2009-09-22', '05');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_order_log', 2, '2009-11-02', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_assign_log', 3, '2009-09-04', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_costbalm', 5, '2009-09-26', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_oilstation', 53, '2013-05-08', '19');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_driver', 1920, '2013-09-17', '12');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_carfeeitem', 19, '2014-07-11', '07');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_cartype', 164, '2014-02-21', '09');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_fitting', 1601, '2013-12-17', '14');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_workingtype', 22, '2014-03-06', '28');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_buymode', 19, '2013-12-31', '06');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_safeitem', 13, '2009-10-23', '25');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_timeperiod', 13, '2009-10-23', '27');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_carowner', 221, '2014-07-13', '08');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_materiel', 6, '2009-10-23', '18');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('assign', 3, '2010-02-22', '03');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('agreement', 1, '2010-02-25', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('route', 31, '2012-11-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_loadword_g', 12, '2010-01-14', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('loadtype', 3, '2009-10-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('loadstyle', 3, '2009-10-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_slsettle', 1, '2010-01-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_assign_pay', 1, '2009-12-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_loadword_income', 13, '2010-01-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stream_model_dot', 48, '2014-10-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_loader', 8, '2010-01-14', '17');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_loaddaily_g', 9, '2010-01-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_loaddaily', 2, '2010-01-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_loadword_fee', 3, '2009-10-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_srsettle', 1, '2010-01-17', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffic_opinion', 1, '2010-11-17', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_trafficobtain', 4, '2009-09-21', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_suppliertype', 9, '2013-06-24', '26');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_ruler', 4, '2010-02-05', '24');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('markstuff', 5, '2010-02-14', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('oprteam', 5, '2010-02-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_order_trace', 1, '2010-02-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_insuranceslip', 1, '2010-11-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_openrequest_g', 1, '2011-05-19', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_payment', 87, '2013-10-24', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_paycancel', 2, '2010-05-20', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomement', 33, '2014-03-14', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomeover', 1, '2010-06-02', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_post', 26, '2013-11-04', '22');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_initcost_g', 2, '2013-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_initcost', 2, '2013-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_initincome', 2, '2013-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_initincome_g', 4, '2010-05-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomeoffset', 9, '2014-02-17', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_cashdepatch', 1, '2012-04-16', '49');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_giver', 8, '2010-06-04', '15');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('vcom_send', 1, '2010-08-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('vcom_informbox', 6, '2010-06-26', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('vcom_maint', 12, '2010-06-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('vcom_maint_g', 5, '2010-06-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('iron_maint_trace', 1, '2010-07-20', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('iron_maintmove', 3, '2010-07-20', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('change_assign', 2, '2012-07-20', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('change_assign_g', 4, '2012-07-20', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_message', 1, '2012-11-29', '02');
commit;
prompt 200 records committed...
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stream_adjust_user', 4, '2012-08-10', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_insurance', 41, '2013-11-05', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_shunter', 20, '2013-06-20', '03');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_chargetax_g', 3, '2013-05-27', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_recoup', 7, '2010-10-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('warstuff', 168, '2010-12-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_feerequest', 2, '2013-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_costaccount', 2, '2013-05-27', '60');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_specialfee', 1, '2010-09-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_pramtrequest', 1, '2014-03-19', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_spsettlecancel', 2, '2010-12-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_rangefee', 1, '2012-04-16', 'RF');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_pramtrequest_g', 1, '2012-12-19', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_rangepamt', 1, '2011-03-22', 'RP');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_cashfeedaily', 1, '2011-04-21', '50');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_cashcostdaily', 1, '2011-05-28', '47');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('supplier_g', 570, '2012-12-28', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_charge_cancel', 7, '2010-10-18', '53');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_spsettlecheck', 3, '2010-10-19', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_chargecancel', 4, '2010-10-21', '56');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_chargecheck', 2, '2010-10-21', '57');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_warecancel', 5, '2010-10-21', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_warecheck', 1, '2010-10-21', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffictemp_g', 3, '2013-08-07', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_traffictemp', 1, '2014-01-14', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_trafficreturn', 4, '2014-02-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_trafficreturn_g', 5, '2013-08-08', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_trafficclaim', 1, '2013-11-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_trafficclaim_g', 2, '2013-08-07', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_trafficclient', 1, '2014-08-26', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_trafficclient_g', 1, '2013-08-08', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_changerequest', 1, '2011-04-01', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_warerequest', 2, '2010-11-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_taxrequest', 1, '2014-03-19', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_report', 1, '2011-12-21', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_rangeware', 1, '2011-01-18', 'RW');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_warerequest_g', 2, '2010-11-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_debourspost', 1, '2011-03-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_feerequest_g', 5, '2013-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_charge_share', 2, '2013-05-27', '55');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_fangkong', 1, '2013-04-18', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_income_g', 2, '2012-04-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_tempserver', 2, '2014-03-12', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_tempserver_g', 1, '2013-04-18', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_tempserver', 1, '2014-07-07', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_tempserver_g', 4, '2013-05-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_tempdetail', 11, '2010-11-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomeload', 3, '2010-12-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomeload_g', 7, '2010-12-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_markbill', 2, '2010-12-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('balmno', 2, '2012-04-16', '04');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_rangetempserver', 2, '2014-03-12', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_rangespsettle', 1, '2010-12-29', 'RP');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_intercourse_g', 2, '2013-05-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_intercourse', 1, '2013-05-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_assertrequest', 2, '2013-05-27', '44');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_assertrequest_g', 3, '2013-05-27', '45');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_spsettle_recoup', 1, '2011-01-14', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_lossbill_g', 7, '2010-12-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_lossbill', 8, '2010-12-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_rangetemp', 1, '2011-01-07', 'RT');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_rangetax', 1, '2011-01-18', 'RX');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_rangespsettle', 2, '2012-04-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_rangeclient', 1, '2011-01-18', 'RC');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_counterother', 2, '2011-01-21', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_counterother_g', 2, '2011-01-21', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_prmoney', 3, '2012-09-03', 'PY');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_rangeprmoney', 1, '2011-01-18', 'RM');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_rangeassert', 1, '2011-01-18', 'RA');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_overhead', 1, '2011-02-22', 'OH');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_overhead_g', 1, '2011-02-22', 'OH');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_rangeover', 1, '2011-01-18', 'RO');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_rangeintercourse', 2, '2011-01-18', 'RI');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_chargetax', 1, '2014-03-19', 'CX');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_chargeassert', 2, '2013-05-27', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_chargeassert_g', 2, '2013-05-27', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('supplier_tran', 36195, '2013-02-24', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_depreciation', 1, '2011-01-28', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_depreciation_g', 1, '2011-01-28', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_initcourse', 2, '2013-05-29', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_initcourse_g', 3, '2013-05-29', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_coursedaily', 3, '2013-05-27', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_nowrequest', 1, '2011-04-21', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_nowrequest_g', 11, '2010-11-03', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_rangenow', 2, '2011-03-23', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_newsmaint', 1, '2014-05-08', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomeincrease', 4, '2011-04-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_newsmaint_g', 3, '2013-08-21', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stream_model_dtuser', 930, '2013-10-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_rangewpsettle', 1, '2011-03-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('cr_carfee_g', 1, '2012-12-05', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_bussessreport', 3, '2011-09-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('rate_aimscost', 2, '2012-08-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sys_trace', 1, '2013-04-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_supplier', 220, '2014-03-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_invoice', 2, '2014-10-16', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('change_order', 2, '2012-07-20', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('change_order_g', 3, '2012-07-20', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('supplier_pick', 9, '2013-02-24', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('supplier_delivery', 13897, '2013-01-12', '01');
commit;
prompt 300 records committed...
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('supplier_load', 8, '2013-01-12', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_helpamt', 2, '2013-01-30', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_helpmove', 1, '2013-01-05', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_insurance', 1, '2014-08-19', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_charge', 1, '2013-12-18', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_carbill', 2, '2014-09-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_carrun', 11, '2014-03-14', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_carfee', 4, '2013-05-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_maintloan', 1, '2010-01-17', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_maintenance', 2, '2013-07-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_maintcharge', 1, '2010-01-17', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_verifloan', 1, '2010-09-06', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_verification', 4, '2014-03-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_verifcharge', 1, '2010-09-06', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_regulations', 188, '2014-03-14', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_caraccident', 1, '2014-02-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_carlon', 1, '2014-03-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_carrepair', 1, '2014-05-23', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_carrepair_g', 20, '2012-12-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_carrepaircharge', 1, '2012-12-04', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_caroil', 2, '2013-05-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_saleryrequest', 1, '2013-05-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_model', 1, '2009-12-20', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_oilmoney', 1, '2012-09-12', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_cardmoney', 3, '2013-05-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_regcharge', 1, '2013-07-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_accidentcharge', 1, '2013-05-12', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_carbill_g', 5, '2013-08-26', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_mainenance', 1, '2012-11-23', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_carold', 1, '2012-11-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_basesalery', 1, '2010-12-21', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_incomeadjust', 1, '2013-05-27', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_intangibleloss', 1, '2013-01-24', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_intangibleloss_g', 1, '2013-01-24', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_clientexamine', 1, '2014-07-15', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_invoice_g', 2, '2013-05-27', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_clientreturn', 1, '2013-07-29', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_clientreturn_g', 1, '2013-07-29', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_requestmaint', 1, '2013-04-10', '52');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_requestmaint_g', 1, '2012-12-04', '13');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_workplan', 3, '2013-04-15', '13');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_workplan_g', 3, '2013-04-15', '13');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_workover', 3, '2013-04-15', '13');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_carborrow', 1, '2014-06-12', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_stuffset', 4, '2012-12-04', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_stuffset_dt', 104, '2012-12-04', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_stuffplan', 4, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_secienty', 2, '2013-09-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_resume', 1, '2013-09-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_base', 164, '2012-12-08', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_turn', 1, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_change', 1, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_leave', 18, '2013-12-19', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_roil', 1, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_teach', 1, '2013-09-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_sarry', 1, '2013-09-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_sarry_g', 110, '2013-09-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_performance', 1, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('getContractNo', 1, '2012-06-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('rate_driver', 1, '2013-05-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_saleryrequest_g', 1, '2012-06-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_raise', 1, '2013-09-22', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_outrequest', 2, '2013-04-15', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_outrequest_g', 3, '2013-04-15', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_carrequest', 1, '2013-04-15', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_carrequest_g', 1, '2012-12-06', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_cardrequest', 1, '2013-05-27', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_cardrequest_g', 2, '2013-05-27', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_overtime', 1, '2013-12-18', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_overtime_g', 1, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sys_trace_g', 2, '2013-04-02', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_smallarea', 3, '2013-01-07', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('rate_area', 5, '2013-07-30', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_type', 314, '2013-07-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_warehouse', 6, '2013-05-07', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_property', 5, '2013-06-30', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_dailyplan', 1, '2013-04-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_dailyover', 1, '2013-04-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_dailyplan_g', 3, '2013-03-23', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_oldtype', 19, '2013-04-02', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_buyrequest', 2, '2013-10-11', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_supplier', 20, '2013-06-30', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_buyrequest_dt', 1, '2013-07-02', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_assertbase', 2, '2013-07-31', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_card', 24, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_goods', 1267, '2013-07-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('com_complaint', 1, '2014-02-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_guahao_type', 4, '2013-04-05', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('customer_trace', 3, '2013-05-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('com_complaint_trace', 47, '2013-04-11', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('customer_home', 2, '2013-05-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('customer_phone', 1, '2013-05-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('customer_plan', 2, '2013-04-24', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('customer_price', 1, '2013-04-24', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_supplier', 10, '2013-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_supplier_g', 2, '2013-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_marketprice_g', 2, '2013-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_market', 1, '2014-05-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_assertrubish', 1, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('com_comclaim', 3, '2013-07-29', null);
commit;
prompt 400 records committed...
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('com_comrecovery', 1, '2013-07-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('com_comstuff', 2, '2013-07-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('problem_problempropose', 3, '2013-09-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_supplier', 8, '2013-11-30', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_buyrequest', 1, '2013-10-20', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_buyrequest_dt', 2, '2013-05-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_intware', 1, '2013-12-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_intware_dt', 2, '2013-07-02', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_baoxiu', 14, '2013-07-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_outware', 1, '2013-05-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_outware_request', 2, '2013-04-11', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_outware_dt', 1, '2013-05-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_assertdepatch', 1, '2013-07-10', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_route', 29, '2013-05-21', '24');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_carcheck', 5, '2013-04-11', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_customertype', 50, '2013-07-02', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_driverlevel', 7, '2013-11-13', '22');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_customerlevel', 16, '2013-04-26', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_paystyle', 25, '2013-09-09', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('rate_type', 262, '2014-08-01', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_oil', 4, '2013-05-07', '13');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_successrate', 22, '2013-05-06', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_unit', 35, '2014-03-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_driverrate', 4, '2013-05-07', '24');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_taosutype', 19, '2013-08-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_diwei', 20, '2013-07-02', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_bitproperty', 20, '2013-05-02', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_regulations', 40, '2013-12-17', '10');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_taosulevel', 19, '2013-08-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_insurancetype', 8, '2013-12-17', '13');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_kaoherate', 12, '2013-08-21', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_culture', 24, '2014-03-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_language', 23, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_region', 11, '2014-03-14', '13');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_folk', 63, '2013-04-12', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_type', 11, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_roiltype', 9, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_kao', 42, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_stand', 31, '2013-11-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('customerprice', 3, '2013-05-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_leave', 2, '2014-07-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_award_g', 1, '2013-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_award', 1, '2013-05-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_outrequest_route', 2, '2013-04-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_system', 2, '2014-04-30', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_type', 60, '2014-04-21', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_overitem', 11, '2013-04-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_jobplanyear', 1, '2013-04-17', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_jobplanyear_g', 3, '2013-04-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_jobplanmonth', 3, '2013-04-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_jobplanweek', 1, '2013-07-23', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_jobplanweek_g', 1, '2013-07-23', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_item', 151, '2014-03-11', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_politics', 8, '2014-01-23', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('stuff_stufftype', 5, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_raketype', 4, '2013-05-21', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_port', 8, '2013-05-28', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('rate_carbalm', 2, '2013-04-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('site_g', 3, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('rate_standbalm', 20, '2013-04-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_noticetype', 11, '2013-08-19', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_newstype', 5, '2013-06-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_serverstyle', 7, '2013-05-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_servertype', 5, '2013-05-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_paytype', 5, '2013-05-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_saletype', 5, '2014-03-20', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_hezuo', 5, '2013-05-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_yunshu', 5, '2013-05-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_salery', 8, '2013-05-28', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_marktype', 6, '2013-05-07', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_ordertype', 3, '2013-05-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_balmstyle', 8, '2013-05-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_backtype', 3, '2013-05-27', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sys_examlog', 1, '2013-08-16', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_oilcardturn', 1, '2013-05-28', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_trafficshare', 2, '2014-02-20', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_trafficlawsuit', 1, '2014-02-26', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_accidenttype', 3, '2013-11-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_acclevel', 1, '2013-05-05', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('com_complaintcarveup', 2, '2013-08-26', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_baditem', 40, '2013-12-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_additem', 19, '2013-11-04', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_poststyle', 10, '2013-11-04', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_goods', 62, '2014-01-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_recoupitem', 20, '2013-12-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_goodslocation', 7, '2013-11-05', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_roadlocation', 2, '2013-05-23', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_customer_trace', 2, '2013-08-23', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_customer_home', 1, '2013-08-24', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_customer_phone', 1, '2013-08-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_customer_plan', 1, '2013-05-11', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_customer_price', 21, '2013-04-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_appraisetype', 18, '2013-11-04', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_checkitem', 9, '2013-05-23', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_appraiseserver', 22, '2013-07-10', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_carddaily', 2497, '2014-10-14', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_cartrace', 1, '2013-12-17', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('problem_problemtype', 11, '2013-12-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('fn_income_order', 4, '2013-09-04', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_assertchange', 1, '2013-07-10', '01');
commit;
prompt 500 records committed...
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_question', 14, '2013-09-24', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_qudong', 2, '2013-08-19', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_repository', 34, '2013-09-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('online_file', 1, '2013-09-30', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_casetype', 31, '2013-09-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('online_file_comment', 12, '2013-09-24', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('online_file_operating', 0, '2013-08-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('problem_file', 1, '2013-08-09', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_bulletitem', 1, '2013-08-30', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_ticketcharge', 2, '2014-03-03', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_badmanager', 1, '2014-04-30', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_maintmanager', 1, '2014-03-19', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_stand', 1, '2013-05-05', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_kao', 1, '2013-05-05', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_performance', 1, '2013-05-05', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_caraccident_g', 4, '2013-07-29', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('modelsite_dot', 3, '2013-06-25', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_carclaiming', 1, '2013-12-15', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_box', 69, '2014-06-18', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_maint', 24, '2013-12-12', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_address12', 1, '2013-06-18', '1');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_officetype', 1, '2010-02-25', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_officegoods', 1, '2010-02-25', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_appraise', 26, '2013-11-08', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_baditem', 10, '2013-06-12', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('maint_change', 1, '2013-08-08', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_address', 205, '2013-06-30', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('car_alertset', 7, '2013-07-02', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_officesupplies_g', 8, '2013-06-19', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_officesupplies', 4, '2013-06-19', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_officeintware', 5, '2013-06-19', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_officeintware_dt', 12, '2013-06-19', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_officeoutware', 4, '2013-06-19', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_officeoutware_dt', 7, '2013-06-19', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_degree', 7, '2013-09-22', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_batchno', 28, '2013-12-16', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('maint_tire', 1, '2013-05-05', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_msgmaint', 1, '2014-06-05', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_msgmaint_g', 9, '2013-06-26', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_intware', 4, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_loadcostrate', 0, '2008-11-06', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_intware_g', 8, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_goodsiodetail', 18, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_income', 1, '2010-10-31', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_outware', 2, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_outware_g', 4, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_staadjust', 1, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_staadjust_g', 2, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_locadjust', 1, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_cost', 19, '2010-10-17', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_locadjust_g', 2, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_storeadjust', 1, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_storeadjust_g', 2, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_stockoutware', 11, '2010-08-30', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_stockoutware_out', 3, '2010-08-30', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_stockoutware_in', 5, '2009-12-29', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_invoice', 0, '2009-10-31', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_loadcost', 2, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_loadcosttype', 0, '2008-12-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_loadincomerate', 0, '2008-12-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_loadcost_account', 0, '2009-10-31', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_loadincome', 2, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_loadincome_account', 0, '2009-10-31', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_costpay', 1, '2011-07-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_costsettle', 0, '2009-10-16', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_carcost', 5, '2010-10-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_carcost_account', 0, '2009-10-31', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_carincome', 5, '2010-10-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_carincome_account', 0, '2009-10-31', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_event', 0, '2008-11-05', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_event_result', 0, '2008-11-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_invoice_cancel', 0, '2009-10-31', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_event_claims', 0, '2008-11-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_cost_g', 36, '2010-10-17', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_intcome_g', 8, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_intcancel', 2, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_income_g', 1, '2010-10-31', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_intcancel_g', 2, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_outcome', 1, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_intcome', 3, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_outcome_g', 2, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_outcancel', 1, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_outcancel_g', 2, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_cycle', 1, '2013-06-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_cycle_g', 3, '2010-10-03', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_batchadjust', 1, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_batchadjust_g', 2, '2013-07-10', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_warehouseincome', 3, '2010-09-07', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_warehouseincome_account', 0, '2009-10-27', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_insidetransfer', 151, '2010-05-31', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_insidetransfer_out', 4, '2010-05-31', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_insidetransfer_in', 4, '2010-05-31', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_income_goods', 47, '2010-10-17', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('ware_outware_pick', 2, '2013-01-26', '02');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_account', 2, '2013-12-31', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('as_assertno', 6909, '2014-03-03', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_levelblame', 6, '2013-08-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_complainttype', 4, '2013-08-13', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('handbillno', 2, '201409', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('contract', 1, '2014-07-31', '02');
commit;
prompt 600 records committed...
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('problem_improve', 1, '2013-09-25', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tc_demaincharge', 4, '2014-03-03', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_rakerequest', 2, '2014-04-01', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_moneyback', 10, '2013-11-04', null);
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_team', 15, '2014-03-06', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_markbill_file', 5, '2014-10-20', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_result', 1, '2013-10-27', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('sale_pricecheck', 2, '2013-11-29', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_pricecheck', 2, '2013-11-29', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('office_contractbalm', 1, '2013-12-24', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('bas_badlevel', 6, '2014-03-07', '01');
insert into SEQTABLE (seq_name, seq_value, seq_time, flag)
values ('tran_payment', 7, '2014-09-26', 'SP');
commit;
prompt 612 records loaded
prompt Enabling triggers for SEQTABLE...
alter table SEQTABLE enable all triggers;
set feedback on
set define on
prompt Done.
