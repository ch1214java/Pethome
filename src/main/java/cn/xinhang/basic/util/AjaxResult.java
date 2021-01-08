package cn.xinhang.basic.util;

import lombok.Data;

@Data
public class AjaxResult {
    private Boolean success = true;
    private String message = "操作成功！";

    public AjaxResult() {
    }

    /**
     * 直接提供一个失败的构造函数
     * @param message
     */
    public AjaxResult(Boolean success, String message) {
        this.success = false;;
        this.message = message;
    }
    public static AjaxResult me(){
        return new AjaxResult();
    }
    public AjaxResult setSuccess(String message) {
        this.success = false;
        this.message = "失败";
        return this;
    }
}
