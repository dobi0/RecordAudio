使い方
①Record_Audio(Mac_ver).jarをダブルクリックもしくはターミナルでjava -jar Record_Audio(Mac_ver).jar 
でアプリケーションが起動する。
②左上に出てくるrecordボタンを押すと、録音が開始され、Enterキーを押すごとにデスクトップがスクリーンショットされる。
③録音終了には、ESCキーを押すと終了する。	

①~③の工程で音声と静止画が取得出来る。取得できたものをブラウザで閲覧したい場合は④以降を実行する必要がある。

④以降はelectronを導入する必要がある。electronを導入するには、node.jsをインストールする必要がある。
node.jsインストール　参考URL　https://qiita.com/akakuro43/items/600e7e4695588ab2958d
electron導入　参考URL https://qiita.com/hal_99/items/6e464f3d45d531ff9336

④ ①~③を実行すると新たにディレクトリが作成されるので、作成されたディレクトリに、slideshowコピー用(audioVer)のディレクトリ内のファイルを全てコピーする。

⑤ ターミナルで作成されたディレクトリに入り、electron ./ を実行する。

Record_Audio(Mac_ver).jar　用途	
・プレゼンテーション発表を録音するためのアプリケーション。スライドショーにして、エンターキーが押される度にスライドがスクリーンショットされ、スライドが切り替わる時間を取得する。