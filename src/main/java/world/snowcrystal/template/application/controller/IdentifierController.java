package world.snowcrystal.template.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import world.snowcrystal.template.domain.common.ApplicationResponse;
import world.snowcrystal.template.domain.identifier.component.SnowFlakeIdentifierGenerator;

@RestController
@RequiredArgsConstructor
@RequestMapping("/identifier")
public class IdentifierController {


    private SnowFlakeIdentifierGenerator snowFlakeIdentifierGenerator;

    public IdentifierController(SnowFlakeIdentifierGenerator snowFlakeIdentifierGenerator) {
        this.snowFlakeIdentifierGenerator = snowFlakeIdentifierGenerator;
    }


    @GetMapping("/snowflake")
    public ApplicationResponse<Long> snowflake() {
        return ApplicationResponse.success(snowFlakeIdentifierGenerator.generate().getValue());
    }

    @GetMapping("/uuid")
    public ApplicationResponse<String> uuid() {
        return ApplicationResponse.success(java.util.UUID.randomUUID().toString());
    }


}
