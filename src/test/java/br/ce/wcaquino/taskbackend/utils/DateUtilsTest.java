package br.ce.wcaquino.taskbackend.utils;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class DateUtilsTest {
    @Test
    public void deveRetornarTrueParaDatasFuturas(){
        LocalDate dataFutura = LocalDate.of(2030,01,01);
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(dataFutura));
    }

    @Test
    public void deveRetornarFalseParaDatasPassadas(){
        LocalDate dataPassada = LocalDate.of(2015,01,01);
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(dataPassada));
    }

    @Test
    public void deveRetornarTrueParaDatasAtuais(){
        LocalDate dataAtual = LocalDate.now();
        Assert.assertTrue(DateUtils.isEqualOrFutureDate(dataAtual));
    }
}
