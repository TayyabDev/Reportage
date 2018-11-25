import matplotlib.pyplot as plt
import csv
import datetime as dt

x = []
y = []
try:
	with open('coords.csv') as csvfile:  # rename to csv data file later on
		plots = csv.reader(csvfile, delimiter=',')
		for row in plots:
			date = row[0].split("/")
			month = int(date[0])
			year = int(date[1])
			obj = dt.datetime(year=year,month=month, day=1)


			x.append(obj.strftime('%b %Y'))
			y.append(int(row[1]))
	print(x)
	print(y)
	# plt.plot(x, y,label='Toronto immigration trend report')

	fig, ax = plt.subplots()
	fig.tight_layout()

	ax.plot(x,y)

	plt.suptitle('Immigration Trends')
	plt.xlabel('Date')
	plt.ylabel('Number of Immigrants')


	plt.savefig('ImmigrationTrend.png')  # to save file to png
except:
	print('error opening file')