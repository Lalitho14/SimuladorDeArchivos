package Implementacion;

public class DirFisica {

  private int direccionLogica;
  private int cilindro;
  private int cara;
  private int sector;

  public DirFisica(int direccionLogica, int cilindro, int cara, int sector) {
    this.direccionLogica = direccionLogica;
    this.cilindro = cilindro;
    this.cara = cara;
    this.sector = sector;
  }

  public int getCilindro() {
    return cilindro;
  }

  public int getCara() {
    return cara;
  }

  public void setCara(int caras, int sectores) {
    this.cara = (direccionLogica / sectores) % caras;
  }

  public int getSector() {
    return sector;
  }

  public void setSector(int sectores) {
    this.sector = direccionLogica % sectores;
  }

  public void setCilindro(int caras, int sectores) {
    this.cilindro = (direccionLogica / sectores) / caras;
  }

  public void setDireccionLogica(int direccionLogica) {
    this.direccionLogica = direccionLogica;
  }

  public int getDireccionLogica(){
    return direccionLogica;
  }

}
