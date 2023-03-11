public class GUIThread implements Runnable {

    private FarmApp farm;

    GUIThread(FarmApp farm){
        this.farm = farm;
    }

    @Override
    public void run() {
        farm.loadDataFromDatabase();
        new FarmAppGUI(farm);
    }
}
