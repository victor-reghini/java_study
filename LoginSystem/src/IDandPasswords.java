import java.util.HashMap;

public class IDandPasswords {

    HashMap<String, String> logininfo = new HashMap<String, String>();

    IDandPasswords(){
        logininfo.put("Victor","senha_segura");
        logininfo.put("Hugo","senha_muito_segura");
        logininfo.put("Reghini","senha_muito_muito_segura");
    }

    protected HashMap getLoginInfo(){
        return logininfo;
    }
}
