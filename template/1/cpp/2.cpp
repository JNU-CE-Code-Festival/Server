#include <iostream>
#include <string>

using namespace std;

string solution(string input);
int main(int argc, char* argv[]) {
    string str(argv[1]);
    string answer(solution(str));
    cout << answer << endl;
    return 0;
}
