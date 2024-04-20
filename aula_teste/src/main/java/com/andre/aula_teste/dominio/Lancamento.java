package com.andre.aula_teste.dominio;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Lancamento implements Serializable {
    private Integer codigoNaturezaDespesa;
    private String descricaoNaturezaDespesa;
    private String descricaoLancamento;
    private Date dataLancamento;
    private Double valorLancamento;

    public Lancamento() {
    }

    public Integer getCodigoNaturezaDespesa() {
        return codigoNaturezaDespesa;
    }

    public void setCodigoNaturezaDespesa(Integer codigoNaturezaDespesa) {
        this.codigoNaturezaDespesa = codigoNaturezaDespesa;
    }

    public String getDescricaoNaturezaDespesa() {
        return descricaoNaturezaDespesa;
    }

    public void setDescricaoNaturezaDespesa(String descricaoNaturezaDespesa) {
        this.descricaoNaturezaDespesa = descricaoNaturezaDespesa;
    }

    public String getDescricaoLancamento() {
        return descricaoLancamento;
    }

    public void setDescricaoLancamento(String descricaoLancamento) {
        this.descricaoLancamento = descricaoLancamento;
    }

    public Date getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(Date dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Double getValorLancamento() {
        return valorLancamento;
    }

    public void setValorLancamento(Double valorLancamento) {
        this.valorLancamento = valorLancamento;
    }

    @Override
    public String toString() {
        return "---- Demonstrativo orçamentário ----- \n " +
                " [" + codigoNaturezaDespesa +"] " +
                descricaoNaturezaDespesa + " -\n " +
                "[" + formatDate(dataLancamento) + "] " +
                descricaoLancamento + " - " +
                "R$ " + valorLancamento;
    }

    private String formatDate (Date dataLancamento) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dataFormatada = formatter.format(dataLancamento);
//        String dataFormatada = formatter.format(dataLancamento.toInstant());
        return dataFormatada;
    }

}
