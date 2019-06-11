
public class test2 {
	ClientMain main;
	JPanel p_west;
	JPanel p_east;
	Choice ch_top;
	Choice ch_sub;
	JTextField t_name;
	JTextField t_price;
	Canvas can_regist;
	// Canvas can_detail;
	JButton bt_find;
	JButton bt_regist;

	// 상위 카테고리의 pk 담을 배열
	ArrayList top_list = new ArrayList();
	ArrayList sub_list = new ArrayList();
	JFileChooser chooser;
	Image regist_img;
	Image regist_img2;
	String regist_path;// 등록시 사용한 이미지 경로
	String imgName;

	// JTable
	ProductTableModel model;
	JTable table;
	JScrollPane scroll;

	// 상세보기관련

	Choice ch_top2;
	Choice ch_sub2;
	JTextField t_name2;
	JTextField t_price2;
	Canvas can_regist2;
	// Canvas can_detail;
	JButton bt_find2;
	JButton bt_edit;
	JButton bt_del;
	URL url;
	int product_id;//선택된 상품의 primary key;
	String img;//현재 선택된 상품의 이미지
	File file;//현재 선택된 파일
	String userData= "C:/Users/itbank/java_developer/data";
	
	public ProductMain(ClientMain main) {
		this.main = main;
		p_west = new JPanel();
		p_east = new JPanel();
		ch_top = new Choice();
		ch_sub = new Choice();
		t_name = new JTextField();
		t_price = new JTextField();

		can_regist = new Canvas() {
			@Override
			public void paint(Graphics g) {
				// 그림 그리기 예정

				g.drawImage(regist_img, 0, 0, 145, 145, null);

			}
		};
		bt_find = new JButton("파일 찾기");
		bt_regist = new JButton("등록");
		chooser = new JFileChooser("E:/java_developer/java/Project0107/res");
		table = new JTable();
		scroll = new JScrollPane(table);

		// 상세보기 new

		p_east = new JPanel();
		ch_top2 = new Choice();
		ch_sub2 = new Choice();
		t_name2 = new JTextField();
		t_price2 = new JTextField();

		can_regist2 = new Canvas() {
			@Override
			public void paint(Graphics g) {
				// 그림 그리기 예정

				g.drawImage(regist_img2, 0, 0, 145, 145, null);

			}
		};
		bt_find2 = new JButton("파일 찾기");
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");

		// 테이블의 row높이 키우기
		table.setRowHeight(65);

		Dimension d = new Dimension(145, 25);

		ch_top.setPreferredSize(d);
		ch_sub.setPreferredSize(d);
		t_name.setPreferredSize(d);
		t_price.setPreferredSize(d);

		can_regist.setPreferredSize(new Dimension(145, 145));
		bt_find.setPreferredSize(d);
		bt_regist.setPreferredSize(d);

		p_west.add(ch_top);
		p_west.add(ch_sub);
		p_west.add(t_name);
		p_west.add(t_price);
		p_west.add(can_regist);
		p_west.add(bt_find);
		p_west.add(bt_regist);
		p_west.setPreferredSize(new Dimension(170, 600));

		// 현재 패널을 BorderLayout 으로
		this.setLayout(new BorderLayout());
		add(p_west, BorderLayout.WEST);
		add(scroll);

		add(p_east, BorderLayout.EAST);
		p_east.add(ch_top2);
		p_east.add(ch_sub2);
		p_east.add(t_name2);
		p_east.add(t_price2);
		p_east.add(can_regist2);
		p_east.add(bt_find2);
		p_east.add(bt_edit);
		p_east.add(bt_del);
		p_east.setPreferredSize(new Dimension(170, 600));

		ch_top2.setPreferredSize(d);
		ch_sub2.setPreferredSize(d);
		t_name2.setPreferredSize(d);
		t_price2.setPreferredSize(d);

		can_regist2.setPreferredSize(new Dimension(145, 145));
		bt_find2.setPreferredSize(d);
		bt_edit.setPreferredSize(d);
		bt_del.setPreferredSize(d);
		
		
		setBackground(Color.GREEN);
		setPreferredSize(new Dimension(1300, 600));
		ch_top.addItemListener(new ItemListener() {


			public void itemStateChanged(ItemEvent e) {
				int index = ch_top.getSelectedIndex();
				Integer obj = (Integer) top_list.get(index);
				getSubList(obj,ch_sub);

			}
		});
		ch_top2.addItemListener(new ItemListener() {


			public void itemStateChanged(ItemEvent e) {
				int index = ch_top2.getSelectedIndex();
				Integer obj = (Integer) top_list.get(index);
				getSubList(obj,ch_sub2);

			}
		});
		// 버튼과 리스너 연결
		bt_find.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openFile(can_regist);
				JOptionPane.showMessageDialog(main, "당신이 등록할 이미지명은 "+img);

			}
		});
		bt_find2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				openFile(can_regist2);
				JOptionPane.showMessageDialog(main, "당신이 수정할 이미지명은 "+img);
			}
		});
		bt_regist.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				upload();
				regist();

			}
		});
		bt_del.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				if(product_id ==0) {
					JOptionPane.showMessageDialog(main, "삭제 상품선택하세요");
					return;
				}
				
				if(JOptionPane.OK_OPTION==JOptionPane.showConfirmDialog(main, "삭제하시겠습니까?")) {
					if(deleteFile()) {//파일먼저 지우고 db지우기;
						delete();//db지우기
						selectAll();
						table.updateUI();
						regist_img2 = null;
						can_regist2.repaint();

					}
			
				}
			}
		});
		bt_edit.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				if(JOptionPane.showConfirmDialog(main, "수정하시겠습니까?") == JOptionPane.OK_OPTION) {
					//상세보기의 이미지명과 우측영역의 파일찾기에 의해 선택된 이미지명이 다르다면 사진교체를 희망
					
					if(regist_img2 != null) {
						System.out.println("원해요");
						upload();
					}
				
					edit();
					selectAll();
					table.updateUI();
				}
				
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				int col = 1;
				//System.out.println(table.getValueAt(row, col));
				product_id =(Integer)table.getValueAt(row, col);//언박싱
				img = (String)table.getValueAt(row, 2);
				System.out.println(img);
				String top_name =(String)table.getValueAt(row, 3);
				
		
				String sub_name = (String)table.getValueAt(row, 4);
				getDetail(product_id,top_name,sub_name);
			}
		});
		// 초이스 컴포넌트에 값 채우기
		getTopList();
		table.setModel(model = new ProductTableModel());
		selectAll();

	}

	// 최상위 카테고리 구하기
	public void getTopList() {

		PreparedStatement pstmt = null;
		Connection con = main.getCon();
		ResultSet rs = null;
		try {
			String sql = "select * from topcategory";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// rs의 값에서 choice로 옮기기;

			while (rs.next()) {

				ch_top.add(rs.getString("name"));
				ch_top2.add(rs.getString("name"));
				// pk도 보관
				// getInt는 int형을 반환 하고 add메서드는 Object형을 인수로
				// 넣어야하므로, 원래 형이 맞이 않아 애러가 났어야하는데
				// 자바에서는 기본자료형과 래퍼클래스가 자동 형변환을 지원한다
				// Integer -->int(unboxing)
				top_list.add(rs.getInt("topcategory_id"));
				//ch_top.select("123");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// 하위 카테고리 구하기
	public void getSubList(int topcategory_id, Choice choice) {
		String sql = "select * from subcategory where topcategory_id = '" + (topcategory_id) + "'";
		Connection con = main.getCon();

		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			choice.removeAll();
			sub_list.removeAll(sub_list);
			// rs.beforeFirst();
			while (rs.next()) {
				choice.add(rs.getString("name"));
				sub_list.add(rs.getInt("subcategory_id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		// System.out.println(sql);
	}

	public void openFile(Canvas can) {
		int result = chooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			file = chooser.getSelectedFile();
			regist_path = file.getAbsolutePath();
			String str =    file.getAbsolutePath();
			System.out.println(str);
			ImageIcon icon = new ImageIcon(str);
			regist_img = icon.getImage();
			regist_img2 = regist_img;
			can.repaint();
			//img = file.getName();
			img = System.currentTimeMillis() + "." + StringUtil.getExt(regist_path);

		}
	}

	// 이미지 특정 위치로 복사(웹이었다면 업로드로 블린다);
	public void upload() {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		img= System.currentTimeMillis() + "." + StringUtil.getExt(regist_path);
		try {

			fis = new FileInputStream(regist_path);
			fos = new FileOutputStream("C:/Users/itbank/java_developer/data"+File.separator+ img);
			System.out.println("C:/Users/itbank/java_developer/data"+File.separator+ img);
			byte[] b = new byte[1024];
			int data = -1;
			while (true) {

				data = fis.read(b);// 입력
			if (data == -1)String fileName
					break;
				fos.write(b);// 출력

			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	public void regist() {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		// ResultSet rs =null;
		int index = ch_sub.getSelectedIndex();
		int subcategory_id = (Integer) sub_list.get(index + 1);
		String name = t_name.getText();
		String price = t_price.getText();

		// System.out.println(imgName);
		String sql = "insert into product(product_id,subcategory_id,product_name,price,img)";
		sql += "values(seq_product.nextval," + subcategory_id + ",'" + name + "'," + price + ",'" + img + "')";
		System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			int result = pstmt.executeUpdate();
			if (result == 1) {
				JOptionPane.showMessageDialog(this, "등록성공");
				selectAll();
			} else {
				JOptionPane.showMessageDialog(this, "등록실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}

	// 모든상품 가져오기(카테고리와 상품테이블의 조인)
	public void selectAll() {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		StringBuffer sb = new StringBuffer();

		sb.append("select t.topcategory_id,t.name as top_name");
		sb.append(" ,s.subcategory_id, s.name as sub_name");
		sb.append(" ,product_id, product_name, price, img");
		sb.append(" from topcategory t, subcategory s, product p");
		sb.append(" where t.topcategory_id = s.topcategory_id");
		sb.append(" and s.subcategory_id = p.subcategory_id");

		System.out.println(sb);

		try {
			// 스크롤 가능 rs
			pstmt = con.prepareStatement(sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = pstmt.executeQuery();
			rs.last();
			int total = rs.getRow();// rs가 위치한 레코드 번호
			// rs를 이용하여 이차원배열을 생성
			Object[][] data = new Object[total][model.columnTitle.length];
			rs.beforeFirst();// 원위치

			for (int i = 0; i < total; i++) {
				rs.next();
				data[i][1] = rs.getInt("product_id");// 고유코드
				data[i][2] = rs.getString("img");
				data[i][3] = rs.getString("top_name");
				data[i][4] = rs.getString("sub_name");
				data[i][5] = rs.getString("product_name");
				data[i][6] = rs.getString("price");
			}

			// model의 이차원배열을 방금 만들어진 배열로 대체
			model.data = data;
			table.updateUI();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void getDetail(int product_id, String top_name,String sub_name) {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("당신이 보게될 상품의 id : "+product_id+"상위 카테고리명은"+top_name);
		StringBuffer sb = new StringBuffer();
		sb.append("select s.subcategory_id as subcategory_id, s.topcategory_id as topcategory_id");
		sb.append(", product_id, product_name, price, img");
		sb.append(" from subcategory s,product p");
		sb.append(" where s.subcategory_id = p.subcategory_id");
		sb.append(" and product_id ="+product_id);
		System.out.println(sb.toString());
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {//한건이긴하지만 커서를 내려야한다.
				//상위카테고리 선택되게
				for(int i=0;i<top_list.size();i++) {
				
					if(ch_top2.getItem(i).equals(top_name)) {
						System.out.println(i+"번째에서 일치합니다");
						ch_top2.select(i);
						
						getSubList((Integer)top_list.get(i), ch_sub2);
					};
				}
				for(int i=0;i<sub_list.size();i++) {
					if(ch_sub2.getItem(i).equals(sub_name)) {
						ch_sub2.select(i);
					}
				}
				//상품명
				t_name2.setText(rs.getString("product_name"));
				t_price2.setText(rs.getString("price"));
				//url = this.getClass().getClassLoader().getResource(rs.getString("img"));
				ImageIcon icon = new ImageIcon(userData + File.separator+ rs.getString("img"));
				regist_img2 = icon.getImage();
				can_regist2.repaint();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		/*
		select s.subcategory_id, s.topcategory_id
		, product_id, product_name, price, img
		from subcategory s,product p
		where s.subcategory_id = p.subcategory_id
		and product_id = pk
		 */
	}
	public boolean deleteFile() {
		boolean result = false;
		File file = new File(userData +File.separator+img);
		System.out.println(file.getAbsolutePath());
		result =file.delete();
		return result;
	}
	//선택된, 데이터한건 삭제
	public void delete() {
		
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		String sql = "delete from product where product_id ="+product_id;
		//System.out.println(sql);
		try {
			pstmt = con.prepareStatement(sql);
			//DML 수행에 의해 영향을 받은 레코드 수를 반환
			int result = pstmt.executeUpdate();
			if(result ==0) {
				JOptionPane.showMessageDialog(main,"실패");
			}else {
				JOptionPane.showMessageDialog(main,"삭제성공");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
	//수정메서드
	public void edit() {
		Connection con = main.getCon();
		PreparedStatement pstmt = null;
		//쿼리문 수행시 바인드 변수를 사용하면 성능 향상이 된다.!!\
		//바인드 변수는 쿼리문의 컴파일과 관련하여 성능을 향상시키기 위함
		//바인드 변수를 지원하는 쿼리문 수행객체가 PreparedStatement
		StringBuffer sb = new StringBuffer();
		sb.append("update product set subcategory_id =?");
		sb.append(", product_name = ?");
		sb.append(", price = ?");
		sb.append(", img = ?");
		sb.append(" where product_id =?");
		try {
			pstmt = con.prepareStatement(sb.toString());
			//?로 대체했던 바인드 변수값을 결정지어야한다.
			pstmt.setInt(1, (int)sub_list.get(ch_sub2.getSelectedIndex()));//subcategory_id
			pstmt.setString(2, t_name2.getText());//product_name
			pstmt.setInt(3, Integer.parseInt(t_price2.getText()));//price
			pstmt.setString(4, img);//img
			pstmt.setInt(5, product_id);//product_id
			int result = pstmt.executeUpdate();
			if(result>0) {
				JOptionPane.showMessageDialog(main, "수정성공");
				System.out.println(t_price2.getText());
			}else {
				JOptionPane.showMessageDialog(main, "실패");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		
		
	}
}
