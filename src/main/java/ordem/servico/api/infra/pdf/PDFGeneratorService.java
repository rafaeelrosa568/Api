package ordem.servico.api.infra.pdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;
import java.util.Map;

import java.io.IOException;


@Service
public class PDFGeneratorService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void export(HttpServletResponse response) throws IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);

//        Paragraph paragraph = new Paragraph("Esse é um titulo", fontTitle);
//        paragraph.setAlignment(Paragraph.ALIGN_CENTER);

        Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
        fontParagraph.setSize(12);

        List<Map<String, Object>> servicos = jdbcTemplate.queryForList("SELECT * FROM servicos WHERE id = true");

        for (Map<String, Object> servico : servicos) {
            Paragraph paragraph = new Paragraph();
            paragraph.add(new Phrase("Cliente: " + servico.get("cliente")));
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(new Phrase("CNPJ/CPF: " + servico.get("cnpjcpf")));
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(new Phrase("Telefone: " + servico.get("telefone")));
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(new Phrase("Endereço: " + servico.get("endereco")));
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(new Phrase("Tipo de Serviço: " + servico.get("tipo_servico")));
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(new Phrase("Valor: " + servico.get("valor")));
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(new Phrase("OS: " + servico.get("os")));
            paragraph.add(Chunk.NEWLINE);
            paragraph.add(Chunk.NEWLINE);
            document.add(paragraph);
        }

        document.close();

    }
}
