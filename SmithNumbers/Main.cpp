
#include <cmath>
#include <list>
#include <iostream>
#include <numeric>

using namespace std;

list<int> * primeFactorization(int n)
{
    list<int> *factors = new list<int>();
    int i, ii;

    while ((n % 2) == 0) {
        factors->push_back(2);
        n /= 2;
    }

    i = 3;
    ii = i * i;
    while (ii <= n) {

        if ((n % i) == 0) {
            factors->push_back(i);
            n /= i;
        } else {
            ii += (i << 2) + 4;
            i += 2;
        }
    }

    if (n > 1) factors->push_back(n);

    return factors;
}

int sumFigures(int n){
    int res = 0;
        while(n != 0){
            res += n%10;
            n = n/10;
        }
    return res;
}

int main(int argc, char const *argv[])
{
    int cases;
    cin >> cases;
    while(cases > 0){
        int n;
        cin >> n;
        int sumN = 0, sumF = 1;
        while(sumN != sumF){
            n++;
            sumN = sumFigures(n);
            list<int> facts = *primeFactorization(n);
            sumF = std::accumulate(std::begin(facts), std::end(facts), 1);
            std::list<int>::iterator f;
            for (auto f = facts.begin(); f != facts.end(); ++f){
                sumF += sumFigures(*f);
            }
        }
        cout << n;
        cases--;
    }
    return 0;
}
