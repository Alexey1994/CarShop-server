package Controllers.Colors;

import CarShop.Models.ColorsFactory;
import CarShop.Models.DAO.ColorsDAO;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import java.util.List;


public class Getter extends ServerResource {
    @Get("txt")
    public String toString(){
        List   colors       = ColorsFactory.getDAO().getAll();
        String resultColors = "[";

        for(int i=0; i<colors.size() - 1; i++)
            resultColors += "\"" + ((ColorsDAO)colors.get(i)).toString() + "\",";

        if(colors.size() > 0)
            resultColors += "\"" + ((ColorsDAO)colors.get(colors.size() - 1)).toString() + "\"";

        resultColors += "]";

        return resultColors;
    }
}
