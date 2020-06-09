package br.com.tcc.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class JurosService {

	public BigDecimal descobreJuros(BigDecimal totalDivida, BigDecimal valorOriginal, int meses) {
		return ((totalDivida.subtract(valorOriginal)).divide(BigDecimal.valueOf(meses)).divide(valorOriginal));
	}
	
	
	public BigDecimal descobreTaxaAdministracaoConsorcio(BigDecimal juros, int meses) {
		return BigDecimal.valueOf(meses).divide(juros);
	}
	
	
	public List<BigDecimal> calculaParcelaComJuros(BigDecimal parcela, BigDecimal juros, int meses) {
		BigDecimal parcelaAtual = parcela.multiply(juros).add(parcela);
		BigDecimal rendimentoMes;
		List<BigDecimal> parcelas = new ArrayList<BigDecimal>();
		parcelas.add(parcelaAtual);
		for (int i = 1; i < meses; i++) {
			// parcelaAtual = mes anterior + parcela do mes
			parcelaAtual = parcelaAtual.add(parcela);
			
			// calcula o redimento da parcela
			rendimentoMes = parcelaAtual.multiply(juros);
			
			// soma o rendimento com a parcela
			parcelaAtual = parcelaAtual.add(rendimentoMes);
			
			parcelas.add(parcelaAtual);
		}
		
		return parcelas;
	}
	
}
