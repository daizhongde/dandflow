<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
<style type="text/css">
/*jquery-ui-bootstrap tabs*/
.tabs ul {
/* border-bottom: 3px solid #39aef5!important;*/
}
.tabs ul li:not(:first-child){
padding-right:15px!important;
  
}
.tabs ul li{
border-top:1px solid #ccc!important;
border-left:1px solid #ccc!important;
/*border-bottom: 1px solid #39aef5!important;*/
}
.tabs ul li a{
color:#666!important;
}
.tabs ul li span:hover{
color:#C61010!important;
}
.tabs ul li a:hover,
.tabs ul li:hover,
.tabs ul li:focus{
border-bottom: 0!important;
}
.tabs ul li:last-child{
border-right:1px solid #ccc!important;
}
.tabs ul li.ui-state-active.ui-tabs-active a,
.tabs ul li.ui-state-active.ui-tabs-active span,
.tabs ul li.ui-state-active.ui-tabs-active{
    /*border-top:3px solid red!important;*/
    background: #39aef5!important;
    border-bottom: 2px solid #39aef5!important;
    color:#ddd!important;
}
.tabs ul li.ui-state-active.ui-tabs-active a:hover,
.tabs ul li.ui-state-active.ui-tabs-active span:hover
{
    color:#fff!important;
}
.tabs ul li .fa-times-circle{
position: absolute;
top: 10px;
right:18px;
}
</style>
 	<link href="../scripts/bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet">
     <link href="../scripts/bootstrap-plugins/metis/2.7.3/css/font-awesome.min.css" rel="stylesheet"> 
<link rel="stylesheet" type="text/css" href="../scripts/jquery-ui-1.12.1/jquery-ui.css"/>
	<script src="../scripts/jquery-2.2.4.min.js"></script>
	<script src="../scripts/bootstrap-3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="../scripts/jquery-ui-1.12.1/jquery-ui.js"/>
<script type="text/javascript">
$(function(){
    var tabs = $( "#tabs3" ).tabs();
               var tabTemplate = "<li><a href='#{href}'>#{label}</a> <span class='fa fa-times-circle'></span></li>";
        $('.menu li').click(function(){
               //获取tabs下a[href]的值
                var id="#tabs-"+this.id;
                //tabs初始化时就有一个li,所以要减1，添加时index会返回-1，再减1变为-2，可根据实际情况而定。这里实际上是通过Id定位#id所在li的位置，然后设置active
                var index=$("#tabs3").find(id).index()-1;
                 $( "#tabs3" ).tabs('option','active',index);
                  if(index==-2){
                     addTab(this.innerText,this.id);
                  }
                 
        });
         
        function addTab(tabTitle,id) {
                var label = tabTitle,
                    id = "tabs-" + id,
                    li = $( tabTemplate.replace( /#\{href\}/g, "#" + id ).replace( /#\{label\}/g, label ) );
                   var tabContentHtml = $("."+id).html();
                    var existing=tabs.find("[id='"+id+"']");
                    if(existing.length==0){
                         tabs.find( ".ui-tabs-nav" ).append( li );
                        tabs.append( "<div id='" + id + "'><p>" + tabContentHtml + "</p></div>" );
                        tabs.tabs( "refresh" );
                    }
                     
                  var index=tabs.find('.ui-tabs-nav li').index(existing);
                  //添加时总是返回-1
                  tabs.tabs('option','active',index);
               
                  }
                // close icon: removing the tab on click
                $( "#tabs3" ).on( "click",'span.fa-times-circle', function() {
                    var panelId = $( this ).closest( "li" ).remove().attr( "aria-controls" );
                    $( "#" + panelId ).remove();
                    tabs.tabs( "refresh" );
                });
     
})
</script>
</head>
<body onload="init();getLog();">
	<h5>动态标签页</h5>
	<ul class="menu">
		<li id="menu1"><a href="#">菜单1</a></li>
		<li id="menu2"><a href="#">菜单2</a></li>
		<li id="menu3"><a href="#">菜单3</a></li>
	</ul>
	<div id="tabs3" class="tabs">
		<ul>
			<li><a href="#tabs-4">主页xxxxx</a></li>

		</ul>
		<div id="tabs-4">主页内容</div>

	</div>
	<div id="tab_content" style="display: none;">
		<div class="tabs-menu1">111</div>
		<div class="tabs-menu2">222</div>
		<div class="tabs-menu3">333</div>
	</div>
</body>
</html>