function erase(){
	var line = new Line();
	line.setColor("#FFFFFF");
	line.remove2(10, 10, 200, 300);//画新
};

var Line = function() {
	var color = "blue";//"#0000ff"
//	var bgcolor = "#FFFFFF";//"#0000ff" erase
	
//	var pointArray = [{ x : 0, y : 0}, { x : 0, y : 0 }];
	var pointArray = [];
	var idocument = document;

	var draw = {
		// 画出最基本的图像，点，和垂直直线或水平直线
		drawBase : function(x1, y1, w, h) {
			try {
				var c=document.getElementById("myCanvas");
				var cxt=c.getContext("2d");
				
//				ctx.globalCompositeOperation = "destination-out";
//				ctx.save();
//				ctx.beginPath();
				
				cxt.moveTo(x1,y1);
				cxt.lineTo(w,h);
				cxt.strokeStyle = color;
				cxt.stroke();
				
//		        ctx.arc(x1,y1,1,0,2*Math.PI);
//		        ctx.fill();
		        
//				setTimeout("ctx.restore();",3000);
				
				
			} catch (e) {
				alert( "catch error" );
			}
		},
		drawLine : function(x1, y1, x2, y2) {
//			alert("drawLine");
			draw.drawBase(x1, y1, x2, y2);
		},
		drawArrowheaded : function(x0, y0, x1, y1) {// 箭头
//			alert("drawArrowheaded");
			var w = (((x1 - x0) == 0 ? 1 : (x1 - x0)));
			var h = (((y1 - y0) == 0 ? 1 : (y1 - y0)));

			var d = Math.sqrt((y1 - y0) * (y1 - y0) + (x1 - x0) * (x1 - x0));
			var Xa = x1 + 10 * ((x0 - x1) + (y0 - y1) / 2) / d;
			var Ya = y1 + 10 * ((y0 - y1) - (x0 - x1) / 2) / d;
			var Xb = x1 + 10 * ((x0 - x1) - (y0 - y1) / 2) / d;
			var Yb = y1 + 10 * ((y0 - y1) + (x0 - x1) / 2) / d;

			draw.drawLine(x1, y1, Xa, Ya);
			draw.drawLine(x1, y1, Xb, Yb);
		},
		drawArrowheadedLine : function(x1, y1, x2, y2) {
			// 直线
			draw.drawLine(x1, y1, x2, y2);
			// 箭头
			draw.drawArrowheaded(x1, y1, x2, y2);
		}
	}

	Line.prototype.drawArrowLine = function(x1, y1, x2, y2) {
//		alert("drawArrowLine");
		
		draw.drawArrowheadedLine(x1, y1, x2, y2);
		var p1 = {x:x1,y:y1};
		pointArray.push(p1);
		var p2 = {x: x2,y: y2};
		pointArray.push(p2);
		
//		pointArray[0].x = x1;
//		pointArray[0].y = y1;
//		pointArray[1].x = w;
//		pointArray[1].y = h;
	}

	Line.prototype.remove = function() {// 删除画出的线
		
		if(pointArray.length==0) return;

		this.color = "#FFFFFF";
		draw.drawArrowheadedLine( pointArray[0].x, pointArray[0].y,
				pointArray[1].x, pointArray[1].y );
		this.color = "blue";
		
		pointArray = null;
		pointArray = [];
	}
	Line.prototype.remove2 = function(x1, y1, x2, y2) {// 删除画出的线
		color = "#FFFFFF";
		draw.drawArrowheadedLine(x1, y1, x2, y2);
		color = "blue";
	}
	
	Line.prototype.setDocument = function(idoc) {
		idocument = idoc;
	}

	Line.prototype.setColor = function(newColor) {// 设置线条颜色
		color = newColor;
	}
}