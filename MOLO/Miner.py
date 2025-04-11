import pulp as PLP

Model = PLP.LpProblem("Opencast_Mining", PLP.LpMaximize)

# Constants
block_size = 50
levels = range(4)
rows = range(4)
cols = range(4)
revenue_per_percent = 200000 / 100
cost = [3000, 6000, 8000, 10000]

# Ore values per level (row x col)
ore_values = {
    0: [
        [1.5, 1.5, 1.5, 0.75],
        [1.5, 2.0, 1.5, 0.75],
        [1.0, 1.0, 0.75, 0.5],
        [0.75, 0.75, 0.5, 0.25]],

    1: [[4.0, 4.0, 2.0],
        [3.0, 3.0, 1.0],
        [2.0, 2.0, 0.5]],

    2: [[12.0, 6.0],
        [5.0, 4.0]],

    3: [[6.0]]
}

# Variables: delta[i][j][k] = 1 if block is extracted
delta = PLP.LpVariable.dicts("delta", (range(4), range(4), range(4)), cat=PLP.LpBinary)

# Objective function: Maximize revenue - cost
Model += PLP.lpSum(
    delta[i][j][k] * ore_values[k][i][j] * revenue_per_percent - delta[i][j][k] * cost[k]
    for k in levels for i in range(4 - k) for j in range(4 - k)
)

# Constraints: Upturned pyramid condition
for k in range(1, 4):
    for i in range(4 - k):
        for j in range(4 - k):
            Model += delta[i][j][k] <= delta[i][j][k - 1]
            Model += delta[i][j][k] <= delta[i + 1][j][k - 1]
            Model += delta[i][j][k] <= delta[i][j + 1][k - 1]
            Model += delta[i][j][k] <= delta[i + 1][j + 1][k - 1]

# Solve model
Model.solve()

# Results
for k in levels:
    print(f"\nLevel {k + 1}")
    for i in range(4 - k):
        row = ""
        for j in range(4 - k):
            row += f"{int(PLP.value(delta[i][j][k]))} "
        print(row)
