import java.util.Scanner;

public class Arvore {
    Node raiz;
    int maior = Integer.MIN_VALUE;
    int menor = Integer.MAX_VALUE;

    public Arvore(){
        this.raiz = null;
    }

    public Node getRaiz(){
        return raiz;
    }

    public int getMaior(){
        return maior;
    }

    public int getMenor(){
        return menor;
    }

    public void inserir(int n){
        Node node = new Node();
        if (n > maior){
            maior = n;
        }
        if (n < menor){
            menor = n;
        }

        node.setInfo(n);
        if (raiz == null) {
            raiz = node;
        }else {
            Node atual = raiz;
            Node esquerda = raiz.getEsquerda();
            Node direita = raiz.getDireita();
            while (atual != null) {
                if (atual.info <= n) {
                    if (atual.getDireita() == null) {
                        atual.setDireita(node);
                        atual.getDireita().setPai(atual);
                        break;
                    } else {
                        atual = atual.getDireita();
                    }
                }else {
                    if (atual.getEsquerda() == null) {
                        atual.setEsquerda(node);
                        atual.getEsquerda().setPai(atual);
                        break;
                    }else {
                        atual = atual.getEsquerda();
                    }
                    }
            }
        }
    }

    public void buscaPreOrdem(Node atual) {
        if (atual == null) {
            return;
        }
        System.out.println(atual.getInfo());

        buscaPreOrdem(atual.getEsquerda());
        buscaPreOrdem(atual.getDireita());
    }

     public void buscaEmOrdem(Node atual) {
        if (atual == null) {
            return;
        }
        buscaEmOrdem(atual.getEsquerda());

        System.out.println(atual.getInfo());

        buscaEmOrdem(atual.getDireita());
    }

    public void buscaPosOrdem(Node atual) {
            if (atual == null) {
            return;
        }

        buscaPosOrdem(atual.getEsquerda());
        buscaPosOrdem(atual.getDireita());

        System.out.println(atual.getInfo());
    }

    public Node remover(Node raiz, int valorParaRemover) {
        if (raiz == null) {
            return raiz;
        }

        if (valorParaRemover < raiz.getInfo()) {
            raiz.setEsquerda(remover(raiz.getEsquerda(), valorParaRemover));
        } else if (valorParaRemover > raiz.getInfo()) {
            raiz.setDireita(remover(raiz.getDireita(), valorParaRemover));
        } else {
            if (raiz.getEsquerda() != null && raiz.getDireita() != null) {
                Node menorMaior = encontrarMenorValor(raiz.getDireita());
                raiz.setInfo(menorMaior.getInfo());
                raiz.setDireita(remover(raiz.getDireita(), menorMaior.getInfo()));
            } else {
                if (raiz.getEsquerda() != null) {
                    raiz = raiz.getEsquerda();
                } else {
                    raiz = raiz.getDireita();
                }
            }
        }

        return raiz;
    }

    private Node encontrarMenorValor(Node nodo) {
        while (nodo.getEsquerda() != null) {
            nodo = nodo.getEsquerda();
        }
        return nodo;
    }

    public void imprime(Node node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.getInfo());
            String newPrefix = prefix + (isLeft ? "│   " : "    ");
            imprime(node.direita, newPrefix, true);
            imprime(node.esquerda, newPrefix, false);
        } else {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + "Vazio");
        }
    }

    public static void main(String[] args) {
        Arvore arvore = new Arvore();

        int[] elementos = {8, 3, 1, 5, 6, 7, 11, 9, 10, 14, 12, 13, 15};

        for (int elemento : elementos) {
            arvore.inserir(elemento);
        }

        Scanner scanner = new Scanner(System.in);

        int opt;
        int valor;
        do {
            System.out.println("[0] sair");
            System.out.println("[1] inserir");
            System.out.println("[2] remover");
            System.out.println("[3] imprimir");
            System.out.println("[4] buscas");
            System.out.print("Escolha uma opção: ");
            opt = scanner.nextInt();

            switch (opt) {
                case 0 -> System.out.println("Até a proxima!");
                case 1 -> {
                    System.out.println("Digite o elemento que deseja inserir: ");
                    valor = scanner.nextInt();
                    arvore.inserir(valor);
                }
                case 2 -> {
                    System.out.println("[1] maior elemento");
                    System.out.println("[2] menor elemento");
                    System.out.println("[3] elemento qualquer");
                    System.out.println("Digite a opção que deseja: ");
                    opt = scanner.nextInt();

                    switch (opt) {
                        case 1 ->{
                            arvore.remover(arvore.getRaiz(), arvore.getMaior());
                        }
                        case 2 ->{
                            arvore.remover(arvore.getRaiz(), arvore.getMenor());
                        }
                        case 3 ->{
                            System.out.println("Digite o valor que deseja remover: ");
                            valor = scanner.nextInt();
                            arvore.remover(arvore.getRaiz(), valor);
                        }
                        default -> {
                            System.out.println("Opção inválida!");
                        }
                    }

                }
                case 3 -> {
                    arvore.imprime(arvore.getRaiz(), "", true);
                }
                case 4 -> {
                    System.out.println("[1] pre ordem");
                    System.out.println("[2] in ordem");
                    System.out.println("[3] pos ordem");
                    System.out.println("Digite qual busca deseja fazer: ");
                    opt = scanner.nextInt();

                    switch (opt) {
                        case 1 ->{
                            arvore.buscaPreOrdem(arvore.getRaiz());
                        }
                        case 2 ->{
                            arvore.buscaEmOrdem(arvore.getRaiz());
                        }
                        case 3 ->{
                            arvore.buscaPosOrdem(arvore.getRaiz());
                        }
                        default -> {
                            System.out.println("Opção inválida!");
                        }
                    }
                }
                default -> {
                    System.out.println("Opção inválida!");
                }
            }
        }while (opt != 0);
    }
}
