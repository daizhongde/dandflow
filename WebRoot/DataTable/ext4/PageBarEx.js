/**
 * 拓展的分页栏，支持用户提供的每页显示条数
   使用方法:
   var store= new Ext.data.Store({
   });
   var pageBar=new PageBarEx({
       store:store,
       width:30,
       pageSize:10
   });
   var grid = new Ext.grid.Panel({
       store: store,
       bbar:pageBar.pageToolBar
   });
   store.load({params:{start:0,limit:pageBar.pageSize}});//注意,这里使用的是pageBar.pageSize
 * @author 蔡玖发
 * @version 1.0
 */
Ext.ns('Ext.ux.grid');

Ext.ux.grid.PageBarEx=function(config){
	this.pageSize=config.pageSize||10;	//默认每页显示10条
	this.maxSize=config.maxSize||1000;	//默认最大每页显示1000条
	this.width=config.width||25;		//输入框的宽度
	this.store=config.store;
	
	var thiz=this;						//引用PageBarEx对象,KeyMap中有用
	this.pageSizeF=new Ext.form.field.Number({
		width:this.width,
		minValue:1,
		value:this.pageSize,
		listeners:{
			render:function(field){
				new Ext.util.KeyMap(field.id,{key:13,fn:function(){
					thiz.setPageSize();
					thiz.store.load({ params: thiz.store.proxy.extraParams });
				}});
			},
			blur:function(field){
				thiz.setPageSize();
			}
		}
	});
	
	this.pageToolBar=new Ext.toolbar.Paging({
		pageSize: this.pageSize,
		store: this.store,
		displayInfo: true,
		displayMsg: '显示 {0} - {1} 共 {2}条',
		emptyMsg: '无数据'
//		items: [{
//		 xtype: 'tbseparator' 
//		}]
	});
	
	this.pageToolBar.add( { xtype: 'tbseparator' } );
	this.pageToolBar.add(this.pageSizeF);
	this.pageToolBar.add('条/页');
	
	/**
	 * 设置每页条数
	 */
	this.setPageSize=function(){
		var pageS=this.pageSizeF.getValue();
		if(pageS!=''&&pageS>0){
			if(pageS>this.maxSize)pageS=this.maxSize;
			this.pageSize=pageS;
		}else{
			this.pageSize=10;
		}
		this.pageSizeF.setValue(this.pageSize);
		this.pageToolBar.pageSize=this.pageSize;
		this.store.proxy.extraParams.limit=this.pageSize;
		this.store.pageSize = this.pageSize;
	};
};
