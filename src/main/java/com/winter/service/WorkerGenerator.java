package com.winter.service;

import com.winter.model.Worker;

import javax.enterprise.context.ApplicationScoped;
import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

@ApplicationScoped
public class WorkerGenerator {
    private static final String[] NAMES = new String[]{"Alex", "Alexey", "John", "Jagadesh", "Joshua", "Ken", "Valentin", "Lourens", "Dmitry", "Charles", "Corbin", "Finn", "Greg", "Jamey", "Tim"};
    private static final String[] LASTNAMES = new String[]{"Ivanchenko", "Bailey", "Bennet", "Carey", "Musk", "Castro", "Wane", "Farley", "Kim", "Lopez", "Marks", "Mcclain", "Meyer", "Nelson", "Petterson", "Parson"};

    public Worker generateDesigner() throws InterruptedException {
        return generateRandomWorker(5, 3, 3);
    }

    public Worker generateProgrammer() throws InterruptedException {
        return generateRandomWorker(3, 5, 3);
    }

    public Worker generateMarketer() throws InterruptedException {
        return generateRandomWorker(3, 3, 5);
    }

    private Worker generateRandomWorker(int maxDesign, int maxProgramming, int maxMarketing) throws InterruptedException {
        Worker worker = new Worker();
        int nameIndex = ThreadLocalRandom.current().nextInt(NAMES.length);
        Thread.sleep(100);
        int lastNameIndex = ThreadLocalRandom.current().nextInt(LASTNAMES.length);

        worker.setName(NAMES[nameIndex] + " " + LASTNAMES[lastNameIndex]);
        worker.setType(ThreadLocalRandom.current().nextInt(3) + 1L);
        worker.setDesign(ThreadLocalRandom.current().nextInt(maxDesign) + 1L);
        worker.setProgramming(ThreadLocalRandom.current().nextInt(maxProgramming) + 1L);
        worker.setMarketing(ThreadLocalRandom.current().nextInt(maxMarketing) + 1L);
        worker.setSalary(new BigDecimal(String.valueOf(ThreadLocalRandom.current().nextInt(100) + 10)));
        worker.setCompanyId(null);
        return worker;
    }

}
