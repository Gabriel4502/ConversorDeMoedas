public class Conversion {
    private String name;
    private double baseValue;
    private String baseCurrency;
    private String currentMoment;
    private String targetCurrency;
    private double convertedValue;
    private double conversionRate;

    public Conversion(ConversionRec convertionRec) {
        this.baseCurrency = convertionRec.base_code();
        this.targetCurrency = convertionRec.target_code();
        this.conversionRate = convertionRec.conversion_rate();
    }

    public void setCurrentMoment(String currentMoment) {
        this.currentMoment = currentMoment;
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public double getBaseValue() {
        return baseValue;
    }

    public void setBaseValue(double baseValue) {
        this.baseValue = baseValue;
    }

    public double convertion (double value){
        this.baseValue = value;
       this.convertedValue= this.baseValue * this.conversionRate;

        return this.convertedValue;
    }

    public double getConvertedValue() {
        return convertedValue;
    }

    @Override
    public String toString() {
        return "\n"+ this.baseValue + " " + this.baseCurrency + " equivalem a " + this.convertedValue +" " + this.targetCurrency + "\n" + this.currentMoment;
    }
}
