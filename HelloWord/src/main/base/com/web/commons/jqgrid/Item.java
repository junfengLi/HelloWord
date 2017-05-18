package com.web.commons.jqgrid;

/** 
 * 功能: 节点的信息 
 */  
public class Item  
{  
     /** 
     * 节点的名字 
     */  
     private String name ;  
      
     /** 
     * 节点的类型："item":文件  "folder":目录 
     */  
     private String type ;  
      
     /** 
     * 子节点的信息 
     */  
     private AdditionalParameters additionalParameters ;  
  
    /* public String getText()  
    {  
          return text ;  
    }  
  
     public void setText(String text )  
    {  
          this .text = text;  
    }  */
     
  
     public String getType()  
    {  
          return type ;  
    }  
  
     public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type )  
    {  
          this .type = type;  
    }  
  
     public AdditionalParameters getAdditionalParameters()  
    {  
          return additionalParameters ;  
    }  
  
     public void setAdditionalParameters(AdditionalParameters additionalParameters )  
    {  
          this .additionalParameters = additionalParameters ;  
    }  
}  