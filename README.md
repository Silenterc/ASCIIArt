# ASCII Art

[![pipeline status](https://gitlab.fit.cvut.cz/BI-OOP/B201/asciiart/badges/master/pipeline.svg)](https://gitlab.fit.cvut.cz/BI-OOP/B201/asciiart)

The idea of this project is to load images, translate them into ASCII ART images, optionally apply filters, and save them. (https://courses.fit.cvut.cz/BI-OOP/projects/ASCII-art.html)

This project´s goal is to showcase OOP oriented design in all its glory. It is written in Scala with maximum scalability and testability in mind. The project includes an extensive amount of unit tests.

## Useful knowledge

The pics are in the src/test/pics folder. The program's arguments can be input according to the assignment.
The **Scale** Filter can have **any** positive float as the scaling parameter and it works reasonably well.

The generated files in the src/test/pics folder are quite random as far as the filters applied on them go, so I recommend manually generating whatever you would like.

**Here are some recommendations**:
1. Create a small lil sussy guy : 

    run --image "src/test/pics/sus.png" --output-file "src/test/pics/smallImposter" --scale 0.001 --table bourkes

2. Create my profile pic but a lil too bright :
   
   run --image "src/test/pics/me.jpeg" --brightness -50 --invert --output-file "src/test/pics/me"

3. Create a star only using " #" : 

   run --image "src/test/pics/star.png" --invert --output-file "src/test/pics/star" --custom-table " #" --scale 1.2

4. Print a small star to console :

   run --image "src/test/pics/star.png" --output-console --table "bourkes" --scale 0.01

5. Create a bird without a beak : 

   run --image "src/test/pics/bird.jpeg" --output-file "src/test/pics/birdWithoutBeak" --table nonlinear --brightness +100

