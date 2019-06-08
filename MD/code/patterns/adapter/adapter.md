
aaaaaaaaaaaaaaaaaaaaaa

aaaaaaaaaaaaaaaaaaaaaaaaaaaa
package patterns.adapter;

public class AirplaneAdapter implements Action {
    private Airplane airplane;

    public AirplaneAdapter(){
        airplane=new Airplane();
    }

    @Override
    public void run() {
        airplane.fly();
    }
}
aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa

aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
