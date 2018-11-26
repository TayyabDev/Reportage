import matplotlib.style as style
import matplotlib.pyplot as plt
import csv
import datetime as dt
from collections import defaultdict

java_fp = 'src/app/java/com/view/ui/createReportViews/'


x = []
y = []

dateMap = defaultdict()
print('here')
with open(java_fp+'ImmigrationCoordinates.csv') as csvfile:  # rename to csv data file later on
	plots = csv.reader(csvfile, delimiter=',')
	for row in plots:
		
		date = row[0].split("/")
		month = int(date[0])
		year = int(date[1])
		obj = str(dt.datetime(year=year,month=month, day=1))


		if obj not in dateMap:
			dateMap[obj] = int(row[1])
		else:
			dateMap[obj] += int(row[1])
		

#			x.append(obj.strftime('%b %Y'))
#			y.append(int(row[1]))

# plt.plot(x, y,label='Toronto immigration trend report')

style.use('ggplot')

plt.rcParams.update({'figure.autolayout': True})



for key in sorted(dateMap):
	L1 = key.split()[0]
	print(L1)
	d1 = ''.join(L1)


	x.append('/'.join(L1.split("-")[0:2][::-1]))
	y.append(dateMap[key])

print(x)
print(y)
fig, ax = plt.subplots()

fig.canvas.set_window_title('Trend Report')

ax.plot(x,y)



plt.suptitle('Immigration Trends')
plt.xlabel('Date')
plt.ylabel('Number of Immigrants')

plt.savefig('ImmigrationTrend.png', dpi=100)
plt.show()
