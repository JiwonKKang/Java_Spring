package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ModelView {
    private String viewname;
    private Map<String, Object> model = new HashMap<>();

    public ModelView(String viewname) {
        this.viewname = viewname;
    }
}
