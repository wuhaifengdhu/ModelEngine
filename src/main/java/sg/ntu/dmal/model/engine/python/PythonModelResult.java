package sg.ntu.dmal.model.engine.python;

import sg.ntu.dmal.model.engine.api.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Usage of this class.
 *
 * @author Wu Haifeng
 * @CreateDate: 20/3/17
 */
public class PythonModelResult implements Result{
    private String result;
    private List<Float> data;

    PythonModelResult(String result){
        this.result = result.trim();
    }

    List<Float> getListData(){
        if(this.data != null) return this.data;
        else {
            data = new ArrayList<>();
        }
        System.out.println("Get result in java: " + result);
        String info = result.substring(1, this.result.length() - 1);
        String[] arr = info.split(",");
        for (String anArr : arr) {
            this.data.add(new Float(anArr.trim()));
        }
        return this.data;
    }

    @Override
    public String toString() {
        return result;
    }
}
