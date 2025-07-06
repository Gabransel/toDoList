package com.gabransel.toDoList.v2.configs;

import com.gabransel.toDoList.v2.entities.TaskV2;
import com.gabransel.toDoList.v2.utils.ModelMapperUtil;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Command implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        TaskV2 t = new TaskV2();
        t.setDescription("teste");

        TaskV2 t2 = new TaskV2();
        t2.setTitle("t4");

        var x = ModelMapperUtil.partialUpdate(t, t2);

        System.out.println(t2);
    }
}
