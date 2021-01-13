# clj8puzzle

Use `lein test` to execute the test suite. 
Use `lein run` to execute the program. You have to provide a sequence of digits 9 unique digits (0..8). Press `<Enter>` to start the solver. 
```text
Enter initial state as sequence of digits 0..8 (i.e. "1 2 3 4 5 6 0 7 8"):
1 2 3 4 5 0 6 7 8
Moving to:
1 2 3
4 5 0
6 7 8

Moving to:
1 2 3
4 5 8
6 7 0

Moving to:
1 2 3
4 5 8
6 0 7

Moving to:
1 2 3
4 5 8
0 6 7

Moving to:
1 2 3
0 5 8
4 6 7

Moving to:
1 2 3
5 0 8
4 6 7

Moving to:
1 2 3
5 6 8
4 0 7

Moving to:
1 2 3
5 6 8
4 7 0

Moving to:
1 2 3
5 6 0
4 7 8

Moving to:
1 2 3
5 0 6
4 7 8

Moving to:
1 2 3
0 5 6
4 7 8

Moving to:
1 2 3
4 5 6
0 7 8

Moving to:
1 2 3
4 5 6
7 0 8

Moving to:
1 2 3
4 5 6
7 8 0
```
