
export class customSort {
    static sortData = sortData();
}

function sortData(key, data) {
    values = []
    let ind = 0
    for (var entry in data) {
        values.append({
            value: entry[key],
            index: ind
        })
    }
    var sortedValues = values.sort(customCompare)
    var sortedData = []
    for (entry in sortedValues) {
        let pos = entry.index
        sortedData.append(data[pos])
    }
    return sortedData
}

function customCompare(a, b)
{
    if(parseInt(a.value) < parseInt(b.value))
        return -1;
    if(parseInt(a.value) > parseInt(b.value))
        return 1;

    return 0;
}
