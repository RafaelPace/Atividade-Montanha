class Doacao {
    private String tipo;
    private String quantidade;
    private String data;

    public Doacao(String tipo, String quantidade, String data) {
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Doação [Tipo: " + tipo + ", Quantidade: " + quantidade + ", Data: " + data + "]";
    }

    public String toFileString() {
        return data + ",    " + quantidade + ",    " + tipo;
    }
}
