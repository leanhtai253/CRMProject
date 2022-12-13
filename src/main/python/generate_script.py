import pandas as pd
import numpy as np

dfs = pd.read_excel("crm_data.xlsx", sheet_name=None)
for sheet in dfs.keys():
    temp = dfs.get(sheet)
    temp = temp.dropna()
    df_num = temp.select_dtypes(include=[float])
    for col in df_num.columns:
        temp[col] = temp[col].apply(lambda x: int(x))
    with open("insert_script.txt", 'a', encoding='utf8') as f:
        f.write(f"-- {sheet}\n")
        for i in range(len(temp)):
            query = f'INSERT INTO {sheet} VALUES ('
            for index,col in enumerate(temp.columns):
                value = temp.loc[i,col]
                if type(value)==np.int64: 
                    query += f'{value}'
                else: query += f"'{value}'"
                # print(f'{temp.loc[i,col]}',end="")
                if index < len(temp.columns)-1:
                    query += ","
                    # print(",",end="")
                else: 
                    # print(")")
                    query += ");"
            f.write(query)
            f.write('\n')
