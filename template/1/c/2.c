#include <stdio.h>

char* solution(char* input);
int main(int argc, char* argv[]) {
    
    char* str=solution(argv[1]);
    printf("%s", str);
    return 0;
}