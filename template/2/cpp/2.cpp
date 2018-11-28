#include <iostream>
#include <string>

using namespace std;

int solution(string str);

int main(int argc, char* argv[]) {
    string str(argv[1]);
    int answer = solution(str);
    cout << answer << endl;
    return 0;
}
