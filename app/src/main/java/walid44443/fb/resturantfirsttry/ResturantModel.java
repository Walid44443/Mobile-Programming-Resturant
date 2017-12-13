package walid44443.fb.resturantfirsttry;

/**
 * Created by walid4444 on 11/23/17.
 */

public class ResturantModel {
    int id;
    String name,ico,phone;
    Double x_axis,y_axis;

    public ResturantModel(int id, String name, Double x_axis, Double y_axis) {
        this.id = id;
        this.name = name;
        this.x_axis = x_axis;
        this.y_axis = y_axis;
    }

    public ResturantModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getX_axis() {
        return x_axis;
    }

    public void setX_axis(Double x_axis) {
        this.x_axis = x_axis;
    }

    public Double getY_axis() {
        return y_axis;
    }

    public void setY_axis(Double y_axis) {
        this.y_axis = y_axis;
    }

    public String getIco() {
        return ico;
    }

    public void setIco(String ico) {
        this.ico = ico;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
