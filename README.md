# Software Engineering Final Coding Assignment (60 points)
**Submission deadline**: 31 August 2020, 11.59pm

You **do not** need to modify this `README.md` file. **No late submission for the coding assignment will be accepted so any late submission after 11.59pm on 31 August 2020 will get a 0.**

## Write Junit test cases (30 points)

You will need to write Junit test cases in `BITreeTest.java` and `ParserTest.java` in `${projectRootPath}/src/test/java` folder for the `${projectRootPath}/src/main/java/BITree.java` and `${projectRootPath}/src/main/java/Parser.java`. 

**ATTENTION:**

- **Do not** change the folder structure, please directly write your `JUnit` test cases in corresponding files.
- **Do not** simplify source code to reduce the complexity of writing test cases. We will test your test file using our original main file.
- **Do not** use **Evosuite** to generate test cases. Otherwise, you will get a **0**.

Run **two** test files with coverage with **JaCoCo** coverage runner. Your score will be the **Branch** coverage rate of Parser class *  **30**.



## Static Analysis Tools (30 points)

You will need to modify `${projectRootPath}/src/main/java/BITree.java` and `${projectRootPath}/src/main/java/Parser.java` to reduce all errors reported by three static analysis tools include **SpotBugs**, **Checkstyle** and **PMD**.  The total number of errors in all three reports is **178**. Your score will be **(178 - errors in your reports) / 178 * 30**.

#### The original reports

| static analysis tools | Errors |
| --------------------- | ------ |
| SpotBugs              | 5      |
| Checkstyle            | 160    |
| PMD                   | 13     |

**ATTENTION:**

1. Rebuild project before run **SpotBugs**.  
2. **Do not** modify `pom.xml`.
3. **Do not** add **@SuppressWarnings** annotation.
4. **Do not** delete source code to reduce errors, we have test cases to ensure the complexity.
5. **Do not** modify or delete  `google_checks.xml` which is provided for **Checkstyle**.

#### Q & A

- **VPN problem: Set Maven mirror** 

  [将maven源改为国内阿里云镜像](https://zhuanlan.zhihu.com/p/71998219)

  `settings.xml` 是 `maven` 的配置文件，用户配置文件存放于 `${user.home}/.m2/` 目录下

- **Maven plugin usages** (IntelliJ IDE)
  
  - **[SpotBugs](https://spotbugs.readthedocs.io/en/stable/maven.html)** 
    - **Goal:** check
      - **Output:** `${projectRootPath}/target/spotbugsXml.xml`
    - **Goal:** gui
    - launches SpotBugs GUI to check analysis result.
  
  - **[CheckStyle](https://maven.apache.org/plugins/maven-checkstyle-plugin/usage.html)**
    - Put `google_checks.xml` to project root path
    - **Goal:** checkstyle
  - ** [checkstyle.html](target/site/checkstyle.html) Output:**.`${projectRootPath}/target/checkstyle-checker.xml` , `${projectRootPath}/target/checkstyle-result.xml`, `${projectRootPath}/target/site/checkstyle.html` ... etc
  
  - **[PMD](https://maven.apache.org/plugins/maven-pmd-plugin/plugin-info.html)**
    - **Goal:** check
    - **Output:**`${projectRootPath}/target/pmd.xml`, `${projectRootPath}/target/site/pmd.html`



### Evaluation (Terminal)

>  **The  way that we will use to score.** 

- **Branch** 

  - **command:** `mvn clean test jacoco:report`

  - **path:** `${projectRootPath}/target/site/jacoco/index.xml`

  - **report:**

    ![coverage](https://github.com/YinZho/se-final-quiz2/blob/master/images/coverage%20report%20example.png)

- **Static Analysis Tool**

  - **SpotBugs**

    - **command:** `mvn spotbugs:check`; `mvn spotbugs:gui`

    - **report:**

      ![spotbugs](https://github.com/YinZho/se-final-quiz2/blob/master/images/spotbugs%20report%20example.png)

  - **Checkstyle**

    - **command:** `mvn checkstyle:checkstyle`

    - **path:** `${projectRootPath}/target/site/checkstyle.html`

    - **report:**

      ![checkstyle](https://github.com/YinZho/se-final-quiz2/blob/master/images/checkstyle%20report%20example.png)

  - **PMD**

    - **command:** `mvn pmd:check`

    - **path:** `${projectRootPath}/target/site/target/site/pmd.html`

    - **report:**

      ![pmd](https://github.com/YinZho/se-final-quiz2/blob/master/images/pmd%20report%20example.png)

      
