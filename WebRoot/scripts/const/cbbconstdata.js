var CBBDATA={};
//C:Connextion  R:Retail
CBBDATA.env =  [ { label: 'Connextion', value: 'C' }, { label: 'Retail', value: 'R' } ];

// F:File  M:Middle Table  D:Target Table
CBBDATA.auditType =  [ { label: 'File', value: 'F' }, 
                       { label: 'Middle Table', value: 'M' }, 
                       { label: 'Target Table', value: 'D' }, 
                       { label: 'File Mid-Table', value: 'FM' } ];
/*   L:less than before,  
   M:more than before,
   D: record number different from before,
   F:field number different from before 
*/
CBBDATA.auditMode =  [ { label: 'less than before', value: 'L' },
                       { label: 'more than before', value: 'M' },
                       { label: 'record number diff', value: 'D' },
                       { label: 'field number diff', value: 'F' } ];