package ykmxxi.board.study;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class ThymeleafStudyTest {

    @Test
    @DisplayName("properties 파일로 값 넣기")
    void external_test() {
        // given
        TemplateEngine templateEngine = createTemplateEngine();

        // when
        String result = templateEngine.process("samples/test1", new Context())
                .trim(); // thymeleaf 개행문자 추가 제거

        // then
        log.info("result={}", result);
        assertThat(result).isEqualTo("<p>Hello World!</p>");
    }

    @Test
    @DisplayName("변수로 값 넣기")
    void variable_test() {
        // given
        TemplateEngine templateEngine = createTemplateEngine();
        Context context = new Context();
        context.setVariable("welcome", "Hello World!");

        // when
        String result = templateEngine.process("samples/test1", new Context())
                .trim(); // thymeleaf 개행문자 추가 제거

        // then
        log.info("result={}", result);
        assertThat(result).isEqualTo("<p>Hello World!</p>");
    }

    private TemplateEngine createTemplateEngine() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L);

        TemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }

}
