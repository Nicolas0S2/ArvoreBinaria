public class Node {
    Node pai;
    Node esquerda;
    int info;
    Node direita;

    public Node(){
        this.pai = null;
        this.esquerda = null;
        this.direita = null;
    }

    public Node(Node n){
        this.pai = n.getPai();
        this.esquerda = n.getEsquerda();
        this.info = n.getInfo();
        this.direita = n.getDireita();
    }

    public Node getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(Node esquerda) {
        this.esquerda = esquerda;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }

    public Node getDireita() {
        return direita;
    }

    public void setDireita(Node direita) {
        this.direita = direita;
    }

    public Node getPai() {
        return pai;
    }

    public void setPai(Node pai) {
        this.pai = pai;
    }
}
