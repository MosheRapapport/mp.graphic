package primitives;

/**
 * This class represents the reflectance of the light
 *
 *
 */
public class Material {
    private double _Kd;
    private double _Ks;
    private double _Kr;
    private double _Kt;
    int _nShininess;


    public double get_Kr() {
        return _Kr;
    }


    public double get_Kt() {
        return _Kt;
    }


    public double get_Kd() {
        return _Kd;
    }

    public double get_Ks() {
        return _Ks;
    }

    public int get_nShininess() {
        return _nShininess;
    }

    /**
     * The constructor receive the different parameters needed to calculate the
     * refelectance of the light
     *
     * @param Kd
     * @param Ks
     * @param nShininess
     */
    public Material(double Kd, double Ks, int nShininess, double Kr, double Kt) {
        _Kd = Kd;
        _Ks = Ks;
        _nShininess = nShininess;
        _Kr=Kr;
        _Kt=Kt;
    }

}