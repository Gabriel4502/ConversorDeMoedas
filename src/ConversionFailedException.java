public class ConversionFailedException extends RuntimeException{

    private String mensagem;

    public ConversionFailedException(String mensagem) {
        this.mensagem = mensagem;
    }

    @Override
    public String toString() {
        return this.mensagem;
    }
}
