var t={
	    "job": {
	        "setting": {
	            "speed": {
	                "channel": 5
	            }
	        },
	        "content": [
	            {
	                "reader": {
	                    "name": "mysqlreader",
	                    "parameter": {
	                        "password": "Wc123456",
	                        "username": "drds_xydjy_sc_dbzx",
	                        "where": "",
	                        "connection": [
	                            {
	                                "querySql": [
	                                    "SELECT
	  id,  n_jgdm,  n_djnd,  n_djhm,  n_djxh,
	  n_kcls,  n_djlx,  n_wlkf,  n_gljg,  n_glnd,
	  n_gldj,  n_glkf,  n_crxz,  c_ppdh,  n_jlsx,
	  n_pply,  c_lyjg,  c_lydj,  n_dxjg,  n_ppqx,
	  c_qxjg,  c_qxdj,  c_qxkh,  n_bzbz,  n_rknd,
	  n_sjjj,  n_jj,  n_jj_tax,  n_jsj,  n_jsj_taxrate,
	  n_jsj_tax,  n_sj,  n_lsj,  n_sl,  n_jsnd,
	  n_jsdh,  n_jszt,  n_rkzt,  n_scbz,  n_tbzt,
	  n_lszt,  c_zy,  c_cwjg,  c_cwbm,  c_gljg,
	  c_glbm,  c_ywdh,  n_sczt,  n_clzt,  c_lyks,
	  n_fj,  n_sjjj_tax,  n_jj_taxrate,  n_jsj_txje,  n_jt,
	  c_chnl,  c_syst,  n_jdjg,  c_thdh,  n_txsj,
	  n_nxsj,  gmt_create,  gmt_modified
	FROM
	  t_fpkc_crkdmx"],
	                                "jdbcUrl": [
	                                    "jdbc:mysql://10.4.230.89:3306?useUnicode=true&characterEncoding=utf8"
	                                ]
	                            }
	                        ]
	                    }
	                },
	              
	          "writer": {
	                    "name": "oraclewriter",
	                    "parameter": {
	                        "column": [
	                        	'id','n_jgdm','n_djnd','n_djhm','n_djxh',
	                        	'n_kcls','n_djlx','n_wlkf','n_gljg','n_glnd',
	                        	'n_gldj','n_glkf','n_crxz','c_ppdh','n_jlsx',
	                        	'n_pply','c_lyjg','c_lydj','n_dxjg','n_ppqx',
	                        	'c_qxjg','c_qxdj','c_qxkh','n_bzbz','n_rknd',
	                        	'n_sjjj','n_jj','n_jj_tax','n_jsj','n_jsj_taxrate',
	                        	'n_jsj_tax','n_sj','n_lsj','n_sl','n_jsnd',
	                        	'n_jsdh','n_jszt','n_rkzt','n_scbz','n_tbzt',
	                        	'n_lszt','c_zy','c_cwjg','c_cwbm','c_gljg',
	                        	'c_glbm','c_ywdh','n_sczt','n_clzt','c_lyks',
	                        	'n_fj','n_sjjj_tax','n_jj_taxrate','n_jsj_txje','n_jt',
	                        	'c_chnl','c_syst','n_jdjg','c_thdh','n_txsj',
	                        	'n_nxsj','gmt_create','gmt_modified'
	],
	                        "writeMode": "insert",
				"preSql": ["select 1"],
	                        "connection": [
	                            {
	                                "jdbcUrl": "jdbc:oracle:thin:@10.3.64.16:1521:jydb",
	                                "table": ["T_FPKC_CRKDMX_BAK20190505"]
	                            }
	                        ],
	                        "username": "test",
	                        "password": "test"
	                    }
	                }
	            
	            }
	        ]
	    }
	}
